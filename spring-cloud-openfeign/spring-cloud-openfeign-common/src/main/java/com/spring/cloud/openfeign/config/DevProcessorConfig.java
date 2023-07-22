package com.spring.cloud.openfeign.config;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * 动态 URL ：解决feign调用时开发环境与正式环指定URL
 *
 * @author lizhifu
 * @since 2023/7/17
 */
@Configuration
@Slf4j
@ConditionalOnProperty(value = "spring.profiles.active",havingValue = "dev")
public class DevProcessorConfig implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(@NotNull Object bean, @NotNull String beanName) throws BeansException {
        log.info("DevProcessorConfig|开发环境配置指定URL|:{}",beanName);
        // 设置微服务调试模式下的的ip端口信息
        //
        System.setProperty("feign.debug.url.base", "http://127.0.0.1:9000");
        System.setProperty("feign.debug.url.data-compilation", "http://127.0.0.1:9011");
        System.setProperty("feign.debug.url.data-spider", "http://127.0.0.1:9106");
        System.setProperty("feign.debug.url.hydro-forecast-short", "http://127.0.0.1:9004");
        System.setProperty("feign.debug.url.hydrodynamic-waterquality-1d", "http://127.0.0.1:9006");
        System.setProperty("feign.debug.url.monitor", "http://127.0.0.1:9103");
        System.setProperty("feign.debug.url.quality-manage", "http://127.0.0.1:9017");
        System.setProperty("feign.debug.url.reservoir-operation", "http://127.0.0.1:9015");
        System.setProperty("feign.debug.url.river-lake-health", "http://127.0.0.1:9016");
        System.setProperty("feign.debug.url.system", "http://127.0.0.1:9002");
        System.setProperty("feign.debug.url.water-resource-allocation", "http://127.0.0.1:9009");
        // 不注册到nacos上,@FeignClient调用指定了url就不会再从nacos上拉取服务信息走负载均衡了
        System.setProperty("spring.cloud.nacos.discovery.register-enabled", "false");
        return bean;
    }

}
