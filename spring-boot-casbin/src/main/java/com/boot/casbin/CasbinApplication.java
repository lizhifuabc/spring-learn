package com.boot.casbin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

/**
 * 启动类
 *
 * @author lizhifu
 * @since 2023/8/18
 */
@SpringBootApplication
@EnableMethodSecurity
public class CasbinApplication {
    public static void main(String[] args) {
        SpringApplication.run(CasbinApplication.class, args);
    }
}
