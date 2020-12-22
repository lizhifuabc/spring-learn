package com.boot.saas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动
 * @author lizhifu
 * @date 2020/12/22
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.boot.saas.mapper"})
public class SpringBootSaaSApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSaaSApplication.class);
    }
}
