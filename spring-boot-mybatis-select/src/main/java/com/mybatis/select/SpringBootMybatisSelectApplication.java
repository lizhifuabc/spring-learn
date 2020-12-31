package com.mybatis.select;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序
 * @author lizhifu
 * @date 2020/12/31
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.mybatis.select.mapper"})
public class SpringBootMybatisSelectApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisSelectApplication.class);
    }
}
