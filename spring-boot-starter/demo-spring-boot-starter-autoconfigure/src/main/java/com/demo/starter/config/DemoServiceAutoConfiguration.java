package com.demo.starter.config;

import com.demo.starter.properties.DemoProperties;
import com.demo.starter.service.DemoService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 配置类
 *
 * @author lizhifu
 * @date 2021/1/15
 */
@Configuration
@ConditionalOnWebApplication // web 应用则配置生效
@EnableConfigurationProperties(DemoProperties.class) // 使得 DemoProperties 生效
public class DemoServiceAutoConfiguration {
    @Resource
    private DemoProperties demoProperties;
    @Bean
    public DemoService helloService(){
        DemoService service = new DemoService(demoProperties.getSay());
        service.say();
        return service;
    }
}
