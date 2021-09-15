package com.boot.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序
 * <p>
 *     启动之后访问路径：http://localhost:8080/learn/
 *     测试:http://localhost:8080/learn/v1.0/
 * </p>
 * @author lizhifu
 * @date 2020/12/24
 */
@SpringBootApplication
public class SpringBootApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApiApplication.class);
    }
}
