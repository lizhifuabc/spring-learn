package com.spring.modulith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Application 启动类
 *
 * @author lizhifu
 * @since 2023/9/15
 */
@EnableAsync
@SpringBootApplication
@ConfigurationPropertiesScan
public class Application {
    public static void main(String... args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
