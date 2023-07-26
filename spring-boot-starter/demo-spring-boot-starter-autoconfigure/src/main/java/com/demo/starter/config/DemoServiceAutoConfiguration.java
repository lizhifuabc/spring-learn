package com.demo.starter.config;

import com.demo.starter.properties.DemoProperties;
import com.demo.starter.properties.ExampleProperties;
import com.demo.starter.service.DemoService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import jakarta.annotation.Resource;

/**
 * 配置类
 * @author lizhifu
 * @since  2021/1/15
 */
@AutoConfiguration    // 自动配置类
@Slf4j
@ConditionalOnWebApplication    // web 应用则配置生效
@EnableConfigurationProperties(value = {DemoProperties.class, ExampleProperties.class})    // 使得 DemoProperties 生效
public class DemoServiceAutoConfiguration {
    @PostConstruct
    public void init() {
        log.info("DemoServiceAutoConfiguration 自动配置类");
    }
    @Resource
    private DemoProperties demoProperties;
    @Bean
    public DemoService helloService(){
        DemoService service = new DemoService(demoProperties.getSay());
        service.say();
        return service;
    }
}
