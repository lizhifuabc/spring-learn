package com.spring.boot.resilience4j;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Resilience4jApplication:启动类
 *
 * @author lizhifu
 * @since 2023/9/11
 */
@SpringBootApplication
@Slf4j
public class Resilience4jApplication {
    public static void main(String[] args) {
        log.info("Resilience4jApplication start...");
        SpringApplication.run(Resilience4jApplication.class, args);
    }
}
