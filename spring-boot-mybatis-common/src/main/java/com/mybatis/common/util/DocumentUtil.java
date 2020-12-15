package com.mybatis.common.util;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;

/**
 * DocumentUtil
 *
 * @author lizhifu
 * @date 2020/12/11
 */
public class DocumentUtil {
    public  static byte[] toBytes(final Document doc) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();

        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DocumentType doctype = doc.getImplementation().createDocumentType(
                "DOCTYPE",
                "-//mybatis.org//DTD Mapper 3.0//EN",
                "http://mybatis.org/dtd/mybatis-3-mapper.dtd");

        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        transformer.transform(new DOMSource(doc), new StreamResult(baos));
        return baos.toByteArray();
    }

    /**
     * 生成根标签
     * @param mapperClassName mapper类名称
     * @return document
     * @throws ParserConfigurationException
     */
    public  static Document initRootElement(final String mapperClassName) throws ParserConfigurationException {
        Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .newDocument();
        Element root = doc.createElement("mapper");
        root.setAttribute("namespace", mapperClassName);
        doc.appendChild(root);
        return doc;
    }
}
