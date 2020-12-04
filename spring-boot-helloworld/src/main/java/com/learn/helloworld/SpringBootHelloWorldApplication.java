package com.learn.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动
 * <p>
 *     启动之后访问路径：http://localhost:8080/learn/
 * </p>
 * @author lizhifu
 * @date 2020/12/4
 */
@SpringBootApplication
public class SpringBootHelloWorldApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootHelloWorldApplication.class);
    }
}
