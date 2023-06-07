package com.spring.cloud.openfeign.two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 启动
 *
 * @author lizhifu
 * @since 2023/6/6
 */
@SpringBootApplication
public class TwoApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TwoApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        System.out.println("系统服务启动成功" + env);
    }
}
