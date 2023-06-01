package com.spring.boot.web;

import com.spring.boot.web.config.ServiceConfiguration;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 启动类
 *
 * @author lizhifu
 * @since 2023/5/31
 */
@SpringBootApplication
@RestController
public class SpringBootWebApplication {
    @Resource
    private ServiceConfiguration configuration;
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }

    @RequestMapping("/configuration")
    public ServiceConfiguration configuration() {
        return configuration;
    }
}
