package com.boot.ehcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 启动程序
 * 文档地址：https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#boot-features-caching-provider-ehcache2
 * https://www.ehcache.org/
 * @author lizhifu
 * @date 2020/12/9
 */
@SpringBootApplication
@EnableCaching
public class SpringBootEhcacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootEhcacheApplication.class);
    }
}
