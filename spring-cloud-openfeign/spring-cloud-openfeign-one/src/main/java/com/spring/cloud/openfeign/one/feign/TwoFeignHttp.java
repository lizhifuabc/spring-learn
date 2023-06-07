package com.spring.cloud.openfeign.one.feign;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * 获取HTTP服务的代理类并放置到IOC容器内
 * @author lizhifu
 * @since 2023/6/7
 */
@HttpExchange("http://127.0.0.1:8082/")
public interface TwoFeignHttp {
    @GetExchange("/two/two")
    String two();
}
