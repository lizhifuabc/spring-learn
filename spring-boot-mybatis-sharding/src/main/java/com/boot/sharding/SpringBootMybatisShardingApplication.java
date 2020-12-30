package com.boot.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序
 * @author lizhifu
 * @date 2020/12/29
 * TODO 分库
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.boot.sharding.mapper"})
public class SpringBootMybatisShardingApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisShardingApplication.class);
    }
}
