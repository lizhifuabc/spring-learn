package com.spring.cloud.openfeign.config;

import feign.Feign;
import feign.Target;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.openfeign.CircuitBreakerNameResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 * Feign与Resilience4j的结合使用
 *
 * @author lizhifu
 * @since 2023/6/7
 */
@Configuration
@Slf4j
public class FooConfiguration {
//    /**
//     *  @Scope("prototype"):
//     *  将这个Feign.Builder的Bean范围设置为prototype,也就是每次注入时返回一个新的实例。
//     * @return
//     */
//    @Bean
//    @Scope("prototype")
//    public Feign.Builder feignBuilder() {
//        return Feign.builder();
//    }


    /**
     * Resilience4j中的CircuitBreaker需要一个名称来标识
     *
     * @return CircuitBreakerNameResolver
     */
    @Bean
    public CircuitBreakerNameResolver circuitBreakerNameResolver() {
        log.info("circuitBreakerNameResolver");
        // feignClientName:Feign client的名称,就是@FeignClient中的value属性
        // method.getName():Feign client方法名
        return (String feignClientName, Target<?> target, Method method) ->{
            log.info("feignClientName:{},method:{}",feignClientName,method.getName());
            return feignClientName + "_" + method.getName();
        };
    }
}
