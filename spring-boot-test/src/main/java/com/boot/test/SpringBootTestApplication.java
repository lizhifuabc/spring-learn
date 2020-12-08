package com.boot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring 启动程序
 * <p>
 *     Starter for testing Spring Boot applications with libraries including JUnit Jupiter, Hamcrest and Mockito
 * </p>
 * <p>
 *     文档地址：https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#boot-features-testing
 * </p>
 * @author lizhifu
 */
@SpringBootApplication
public class SpringBootTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTestApplication.class, args);
    }
}
