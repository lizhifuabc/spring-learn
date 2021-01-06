package com.mybatis.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序
 * @author lizhifu
 * @date 2021/1/6
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.mybatis.provider.demo.mapper"})
public class SpringBootMybatisProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisProviderApplication.class);
    }
}
