package com.boot.idempotent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序
 * <p>
 *     启动之后访问路径：<a href="http://localhost:8080/learn/">...</a>
 * </p>
 * @author lizhifu
 * @date 2020/12/15
 */
@SpringBootApplication
public class SpringBootIdempotentApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootIdempotentApplication.class);
    }
}
