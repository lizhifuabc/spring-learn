package com.spring.cloud.openfeign;

import com.spring.cloud.openfeign.config.DevProcessorConfig;
import com.spring.cloud.openfeign.config.FeignLoggerConfiguration;
import com.spring.cloud.openfeign.config.FooConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 自动配置
 *
 * @author lizhifu
 * @since 2023/5/2
 */
@Configuration(proxyBeanMethods = false)
@Import({FeignLoggerConfiguration.class, FooConfiguration.class, DevProcessorConfig.class})
public class AutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(AutoConfiguration.class);
    @PostConstruct
    public void postConstruct() {
        log.info("spring cloud feign 自动配置");
    }
}
