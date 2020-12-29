package com.boot.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序
 * <p>
 *     https://shardingsphere.apache.org/index_zh.html
 * </p>
 * @author lizhifu
 * @date 2020/12/4
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.boot.sharding.mapper"})
public class SpringBootShardingApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootShardingApplication.class);
    }
}
