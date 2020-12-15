package com.mybatis.common;

import com.mybatis.common.annotation.MapperXmlElement;
import com.mybatis.common.base.ParentMapper;
import com.mybatis.common.element.BaseElement;
import com.mybatis.common.util.ClassUtils;
import com.mybatis.common.util.DocumentUtil;
import com.mybatis.common.util.StrUtils;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.builder.xml.XMLStatementBuilder;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.util.Map;

/**
 * MyBatisConfiguration 基础配置
 * <p>初始化bean的时候执行afterPropertiesSet()</p>
 * @author lizhifu
 * @date 2020/12/14
 */
public class MyBatisConfiguration implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(MyBatisConfiguration.class);
    @Resource
    private ApplicationContext applicationContext;
    /**
     * 当多数据源时，默认都注入
     */
    @Resource
    private SqlSessionFactory[] sqlSessionFactories;
    @Override
    public void afterPropertiesSet() throws Exception {
        //无法获取SqlSessionFactory，此时直接返回
        //不用抛出异常，因为可能应用并没有SqlSessionFactory
        if (sqlSessionFactories == null || sqlSessionFactories.length == 0) {
            logger.warn("没有找到 'SqlSessionFactory',直接返回");
            return;
        }
        String path = "classpath*:base-mybatis-template.xml";
        org.springframework.core.io.Resource[] resources = new PathMatchingResourcePatternResolver().getResources(path);
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactories) {
            Configuration configuration = sqlSessionFactory.getConfiguration();
            //可复用sql片段
            Map<String, XNode> sqlFragments = configuration.getSqlFragments();
            //解析所以得sql片段
            new XMLMapperBuilder(
                    /*XML文件输入流*/
                    resources[0].getInputStream(),
                    /*Mybatis用户全局配置*/
                    configuration,
                    /*资源配置文件的地址*/
                    resources[0].toString(),
                    /*已有的SQL代码块*/
                    sqlFragments)
                    /*解析该XML文件*/
                    .parse();
            for (Class mapper : configuration.getMapperRegistry().getMappers()) {
                if (ParentMapper.class.isAssignableFrom(mapper)) {
                    //创建Document mapper namespace
                    Document doc = DocumentUtil.initRootElement(mapper.getName());
                    //namespae 前缀
                    String namespacePrefix = mapper.getName() + ".";
                    //返回根节点<mapper/>
                    Element rootElement = doc.getDocumentElement();
                    //基础element
                    rootElement.appendChild(BaseElement.genTable(doc,mapper));
                    rootElement.appendChild(BaseElement.genColumn(doc,mapper));

                    //遍历mapper继承的接口的所有注解
                    for (MapperXmlElement mapperXmlEl : ClassUtils.getMapperXmlElement(mapper)) {
                        String id = namespacePrefix + mapperXmlEl.id();
                        //设置元素的开始结束：<select></select>
                        Element element = doc.createElement(mapperXmlEl.id().toLowerCase());
                        //设置ID：<select id="XXX"></select>
                        element.setAttribute("id", mapperXmlEl.id());
                        if(!StrUtils.isBlank(mapperXmlEl.include())){
                            //创建<select></select>内部的<include refid="selectDeptVo"/>
                            Element includeElement = doc.createElement("include");
                            String include = mapperXmlEl.include();
                            includeElement.setAttribute("refid", StrUtils.isNotBlank(include) ?include : "BaseMybatisTemplate." + mapperXmlEl.id());
                            //开始元素加入include，<select></select>
                            element.appendChild(includeElement);
                        }
                        rootElement.appendChild(element);
                    }
                    byte[] bytesMapper = DocumentUtil.toBytes(doc);
                    logger.info("Mapper XML [] generated.", new String(bytesMapper));
                    new XMLMapperBuilder(
                            new ByteArrayInputStream(bytesMapper),
                            configuration,
                            "[BaseMybatis]" + mapper.getName() + ".xml",
                            sqlFragments).parse();
                }
            }
        }
    }
}
