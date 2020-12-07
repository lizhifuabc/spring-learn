package com.boot.gen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动
 * @author lizhifu
 * @date 2020/12/4
 * TODO 尚未开始
 */
@MapperScan("com.boot.gen.mapper")
@SpringBootApplication
public class SpringBootGenApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootGenApplication.class);
    }
}
