package com.boot.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动程序
 * @author lizhifu
 * @date 2020/12/29
 */
@SpringBootApplication
@EnableAsync
public class SpringBootLogCustomApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootLogCustomApplication.class);
    }
}
