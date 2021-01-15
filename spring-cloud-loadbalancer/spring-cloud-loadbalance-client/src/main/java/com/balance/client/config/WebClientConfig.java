package com.balance.client.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * WebClientConfig
 *
 * @author lizhifu
 * @date 2021/1/15
 */
@Configuration
@LoadBalancerClient(name = "say-hello", configuration = SayHelloConfiguration.class)
public class WebClientConfig {
    @LoadBalanced
    @Bean
    WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
