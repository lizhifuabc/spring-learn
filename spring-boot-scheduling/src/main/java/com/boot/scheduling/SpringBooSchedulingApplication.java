package com.boot.scheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动程序
 * @EnableScheduling 开启定时任务
 * @author lizhifu
 * @date 2020/12/17
 */
@SpringBootApplication
@EnableScheduling
public class SpringBooSchedulingApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBooSchedulingApplication.class);
    }
}
