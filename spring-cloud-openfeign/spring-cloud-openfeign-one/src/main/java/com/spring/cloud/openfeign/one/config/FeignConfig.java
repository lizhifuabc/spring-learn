package com.spring.cloud.openfeign.one.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * feign 配置
 *
 * @author lizhifu
 * @since 2023/6/7
 */
public class FeignConfig {
    @Bean
    public Logger.Level feignLogLevel(){
        //设置feign客户端的日志打印级别为FULL
        return Logger.Level.FULL;
    }
}
