package com.boot.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * spring 启动程序
 * <p>
 *     官方文档地址：
 *     https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#boot-features-task-execution-scheduling
 * </p>
 * @see EnableAsync 开启异步任务
 * @author lizhifu
 */
@SpringBootApplication
@EnableAsync
public class SpringBootAsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAsyncApplication.class, args);
    }
}
