package com.boot.lombok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序
 * https://github.com/rzwitserloot/lombok
 * https://projectlombok.org/features/all
 * <p>
 *     SpringBoot 2.1.x版本后无需指定Lombok版本
 *     SpringBoot在spring-boot-dependencies中已经内置
 * </p>
 * @author lizhifu
 * @date 2020/12/29
 */
@SpringBootApplication
public class SpringBootLombokApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class);
    }
}
