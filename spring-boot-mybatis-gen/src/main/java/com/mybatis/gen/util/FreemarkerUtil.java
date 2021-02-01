package com.mybatis.gen.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * 模板解析
 *
 * @author lizhifu
 * @date 2021/2/1
 */
public class FreemarkerUtil {
    public static String processString(String templateName, Map<String, Object> params) throws IOException, TemplateException {
        Configuration freemarkerConfig = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        String templatePath = FreemarkerUtil.class.getResource("/").getPath()+"/model";
        freemarkerConfig.setDirectoryForTemplateLoading(new File(templatePath));
        freemarkerConfig.setDefaultEncoding("UTF-8");
        freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        freemarkerConfig.setLogTemplateExceptions(false);
        freemarkerConfig.setWrapUncheckedExceptions(true);
        freemarkerConfig.setDefaultEncoding("UTF-8");
        Template template = freemarkerConfig.getTemplate(templateName, "UTF-8");
        StringWriter result = new StringWriter();
        template.process(params, result);
        return result.toString();
    }
}
