package com.spring.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 启动类
 * TODO 通过定时启动 Job来实现数据的实时采集与同步
 *
 * @author lizhifu
 * @since 2023/5/31
 */
@SpringBootApplication
public class SpringBootBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootBatchApplication.class, args);
    }
}
