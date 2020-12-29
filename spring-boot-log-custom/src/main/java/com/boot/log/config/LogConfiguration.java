package com.boot.log.config;

import com.boot.log.log.AbstractLog;
import com.boot.log.log.MongoAbstractLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配类
 *
 * @author lizhifu
 * @date 2020/12/29
 */
@Configuration
public class LogConfiguration {
    @Bean
    public AbstractLog abstractLog(){
        return new MongoAbstractLog();
    }
}
