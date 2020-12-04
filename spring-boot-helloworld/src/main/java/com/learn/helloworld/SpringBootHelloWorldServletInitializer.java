package com.learn.helloworld;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * spring boot 在web容器(例如:tomcat)中启动
 *
 * @author lizhifu
 * @date 2020/11/30
 */
public class SpringBootHelloWorldServletInitializer extends SpringBootServletInitializer {
    /**
     * 覆盖 configure 方法
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(SpringBootHelloWorldApplication.class);
    }
}
