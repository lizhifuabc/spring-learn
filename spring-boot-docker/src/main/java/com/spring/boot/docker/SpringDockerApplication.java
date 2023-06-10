package com.spring.boot.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 启动
 *
 * @author lizhifu
 * @since 2023/6/10
 */
@RestController
@SpringBootApplication
public class SpringDockerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringDockerApplication.class, args);
    }
    @GetMapping("/")
    public String docker() {
        return "hello docker";
    }
}
