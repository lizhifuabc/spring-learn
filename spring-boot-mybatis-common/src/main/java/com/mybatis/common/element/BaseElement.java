package com.mybatis.common.element;

import com.mybatis.common.annotation.Table;
import com.mybatis.common.base.ParentMapper;
import com.mybatis.common.enums.XmlIdTag;
import com.mybatis.common.enums.XmlTag;
import com.mybatis.common.xml.ColumnXml;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.annotation.AnnotationUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 基础sql片段
 * @author lizhifu
 * @date 2020/12/15
 */
public class BaseElement {
    /**
     *  <sql id="table">tb_user</sql>
     * @param doc
     * @param mapperClass
     * @return
     */
    public static Element genTable(final Document doc,final Class mapperClass){
        Class entityClass = GenericTypeResolver.resolveTypeArguments(
                        mapperClass, ParentMapper.class)[0];

        Element element = doc.createElement(XmlTag.SQL.name().toLowerCase());
        element.setAttribute("id", XmlIdTag.TABLE.name().toLowerCase());
        Table annotation =  AnnotationUtils.findAnnotation(entityClass, Table.class);
        element.setTextContent(annotation.value());
        return element;
    }

    /**
     * <sql id="cols">id,name</sql>
     * @param doc
     * @param mapperClass
     * @return
     */
    public static Element genColumn(final Document doc,final Class mapperClass){
        Class entityClass = GenericTypeResolver.resolveTypeArguments(
                mapperClass, ParentMapper.class)[0];
        Element element = doc.createElement(XmlTag.SQL.name().toLowerCase());
        element.setAttribute("id", XmlIdTag.COLUMN.name().toLowerCase());
        element.setTextContent(ColumnXml.getColumns(entityClass));
        return element;
    }
}
