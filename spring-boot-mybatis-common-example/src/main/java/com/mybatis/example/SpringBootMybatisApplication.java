package com.mybatis.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动
 *
 * @author lizhifu
 * @date 2020/12/11
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.mybatis.example"})
public class SpringBootMybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisApplication.class);
    }
}
