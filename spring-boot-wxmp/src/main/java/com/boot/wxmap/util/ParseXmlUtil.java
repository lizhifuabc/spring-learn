package com.boot.wxmap.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * xml数据转换
 * @author lizhifu
 */
@Slf4j
public class ParseXmlUtil {
	/**
	 * 将bean转换为xml
	 * @param obj 转换的bean
	 * @return bean转换为xml
	 */
	public static String objectToXml(Object obj) {
		//解决下划线问题
		XStream xStream = new XStream(new DomDriver() {
			@Override
			public HierarchicalStreamWriter createWriter(Writer out) {
				return new MyWriter(out);}});
		//xstream使用注解转换
		xStream.processAnnotations(obj.getClass());
		return xStream.toXML(obj);
	}
	public static class MyWriter extends PrettyPrintWriter {
		public MyWriter(Writer writer) {
			super(writer);
		}
		@Override
		protected void writeText(QuickWriter writer, String text) {
			writer.write(text);
		}
	}
	/**
	 * xml转map
	 * @param xmlStr
	 * @return
	 */
	public static Map parseTo(String xmlStr) {
		if(StringUtils.isEmpty(xmlStr)){
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		String FEATURE = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			// This is the PRIMARY defense. If DTDs (doctypes) are disallowed, almost all XML entity attacks are prevented
			// Xerces 2 only - http://xerces.apache.org/xerces2-j/features.html#disallow-doctype-decl
			FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
			factory.setFeature(FEATURE, true);

			// If you can't completely disable DTDs, then at least do the following:
			// Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-general-entities
			// Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-general-entities
			// JDK7+ - http://xml.org/sax/features/external-general-entities
			FEATURE = "http://xml.org/sax/features/external-general-entities";
			factory.setFeature(FEATURE, false);

			// Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-parameter-entities
			// Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-parameter-entities
			// JDK7+ - http://xml.org/sax/features/external-parameter-entities
			FEATURE = "http://xml.org/sax/features/external-parameter-entities";
			factory.setFeature(FEATURE, false);

			// Disable external DTDs as well
			FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
			factory.setFeature(FEATURE, false);

			// and these as well, per Timothy Morgan's 2014 paper: "XML Schema, DTD, and Entity Attacks"
			factory.setXIncludeAware(false);
			factory.setExpandEntityReferences(false);

			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlStr
					.toString())));

			Element root = doc.getDocumentElement();
			NodeList books = root.getChildNodes();
			if (books != null) {
				for (int i = 0; i < books.getLength(); i++) {
					Node book = books.item(i);
					if(book != null && book.getFirstChild() != null){
						map.put(book.getNodeName(), book.getFirstChild()
								.getNodeValue());
					}
				}
			}
		} catch (Exception e) {
			log.error("parse xml error:", e);
		}
		return map;
	}
}
