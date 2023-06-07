package com.spring.cloud.openfeign.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 启动
 *
 * @author lizhifu
 * @since 2023/6/6
 */
@SpringBootApplication
@EnableDiscoveryClient //开启服务注册与发现
@EnableFeignClients(basePackages = {"com.spring.cloud.openfeign.one.feign"}) //开启feign
public class OneApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(OneApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        System.out.println("系统服务启动成功" + env);
    }
}
