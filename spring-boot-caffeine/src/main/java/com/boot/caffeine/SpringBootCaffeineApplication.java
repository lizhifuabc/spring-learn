package com.boot.caffeine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 启动程序
 * 添加注解,开启缓存支持{@link EnableCaching}
 * TODO 文档完善
 * @author lizhifu
 * @date 2020/12/25
 */
@SpringBootApplication
@EnableCaching// 开启缓存，需要显示的指定
@MapperScan(basePackages = {"com.boot.caffeine.mapper"})
public class SpringBootCaffeineApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootCaffeineApplication.class);
    }
}
