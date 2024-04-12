package com.spring.modulith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Application 启动类
 *
 * @author lizhifu
 * @since 2023/9/15
 */
//@EnableAsync
@SpringBootApplication
public class SpringModulithApplication {
    public static void main(String[] args) {
        System.out.println("启动");
        SpringApplication.run(SpringModulithApplication.class, args);
    }
}
