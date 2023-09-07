package com.spring.bean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动
 *
 * @author lizhifu
 * @since 2023/9/7
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("启动");
        SpringApplication.run(Application.class, args);
    }
}
