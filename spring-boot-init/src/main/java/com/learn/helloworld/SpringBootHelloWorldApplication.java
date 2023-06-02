package com.learn.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序
 * 简单演示几种初始化时候的方法
 * 可以用于缓存预热等等预处理的方法
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
