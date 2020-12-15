package com.mybatis.common.annotation;

import com.mybatis.common.enums.XmlTag;
import com.mybatis.common.xml.EmptyXmlContent;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 生成的mapper.xml内包含的内容
 *
 * @author lizhifu
 * @date 2020/12/14
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MapperXmlElement {
    /**
     * id:<insert id="insert"/>
     * @return id
     */
    String id();

    /**
     * XML标签 <select resultType=""/>
     * @return
     */
    String resultType() default "";

    /**
     * XML 标签<insert/>
     * @return
     */
    XmlTag xmlTag();
    /**
     * XML标签 <select include=""/>
     * @return
     */
    String include() default "";

    Class xmlContent() default EmptyXmlContent.class;
}
