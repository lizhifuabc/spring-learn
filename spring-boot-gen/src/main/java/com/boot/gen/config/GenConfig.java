package com.boot.gen.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 读取代码生成相关配置
 * @author lizhifu
 */
@Component
@Data
@ConfigurationProperties(prefix = "gen")
@PropertySource(value = { "classpath:gen.properties" })
public class GenConfig
{
    /** 作者 */
    public String author;

    /** 生成包路径 */
    public String packageName;

    /** 自动去除表前缀，默认是false */
    public boolean autoRemovePre;

    /** 表前缀(类名不会包含表前缀) */
    public String tablePrefix;
    /** moduleName名称 */
    public String moduleName;
    /** 功能名称 */
    public String functionName;
    /** java类型 */
    public Map<String, String> javaType;
}
