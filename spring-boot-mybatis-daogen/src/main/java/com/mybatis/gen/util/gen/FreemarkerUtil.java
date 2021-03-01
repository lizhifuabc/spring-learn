package com.mybatis.gen.util.gen;

import com.mybatis.gen.util.str.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * 模板解析
 *
 * @author lizhifu
 * @date 2021/2/1
 */
public class FreemarkerUtil {
    /**
     * 模板加载
     * @param templateName 名称
     * @param params 参数
     * @param templatePath 在测试环境下，需要填写
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static String processString(String templateName, Map<String, Object> params,String templatePath) throws IOException, TemplateException {
        Configuration freemarkerConfig = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        if(StringUtils.isEmpty(templatePath)){
            templatePath = FreemarkerUtil.class.getResource("/").getPath()+"/model";
        }
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
