package com.boot.hot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动程序
 * @author lizhifu
 * @date 2020/12/18
 */
@SpringBootApplication
@EnableTransactionManagement
public class SpringBootHotAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootHotAccountApplication.class);
    }
}
