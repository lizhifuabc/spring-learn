package com.balance.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * 启动
 * https://spring.io/guides/gs/spring-cloud-loadbalancer/
 * @author lizhifu
 * @date 2021/1/15
 */
@SpringBootApplication
@RestController
public class LoadbalancerClientApplication {
    private final WebClient.Builder loadBalancedWebClientBuilder;
    private final ReactorLoadBalancerExchangeFilterFunction lbFunction;

    public LoadbalancerClientApplication(WebClient.Builder webClientBuilder,
                                         ReactorLoadBalancerExchangeFilterFunction lbFunction) {
        this.loadBalancedWebClientBuilder = webClientBuilder;
        this.lbFunction = lbFunction;
    }

    public static void main(String[] args) {
        SpringApplication.run(LoadbalancerClientApplication.class, args);
    }
    @RequestMapping("/say")
    public Mono<String> say() {
        return loadBalancedWebClientBuilder.build().get().uri("http://say-hello/say")
                .retrieve().bodyToMono(String.class)
                .map(say -> say);
    }

    @RequestMapping("/sayHello")
    public Mono<String> hello() {
        return WebClient.builder()
                .filter(lbFunction)
                .build().get().uri("http://say-hello/sayHello")
                .retrieve().bodyToMono(String.class)
                .map(sayHello -> sayHello);
    }
}
