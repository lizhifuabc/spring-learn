package com.starter.test;

import com.demo.starter.annotation.EnableConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序
 * @author lizhifu
 * @since  2021/1/15
 */
@SpringBootApplication
@EnableConfig
public class SpringBootStarterTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarterTestApplication.class);
    }
}
