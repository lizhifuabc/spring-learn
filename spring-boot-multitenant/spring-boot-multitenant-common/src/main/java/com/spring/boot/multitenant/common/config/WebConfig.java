package com.spring.boot.multitenant.common.config;

import com.spring.boot.multitenant.common.interceptor.TenantIdInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig
 *
 * @author lizhifu
 * @since 2023/6/12
 */
@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {
    private final TenantIdInterceptor tenantIdInterceptor;

    public WebConfig(TenantIdInterceptor tenantIdInterceptor) {
        this.tenantIdInterceptor = tenantIdInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("添加拦截器:{}", tenantIdInterceptor.getClass().getName());
        registry.addInterceptor(tenantIdInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
