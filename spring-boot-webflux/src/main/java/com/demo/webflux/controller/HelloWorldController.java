package com.demo.webflux.controller;

import io.netty.util.internal.ThreadLocalRandom;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.Map;
import java.util.Random;

/**
 *  HelloWorldController
 *
 * @author lizhifu
 * @date 2021/1/15
 */
@RestController
public class HelloWorldController {
    /**
     * webflux-web
     * @return
     */
    @GetMapping("webflux")
    public Mono<String> webflux() {
        Random r = new Random();
        return Mono.just("webflux"+r.nextInt());
    }

    /**
     * 响应式Http
     *
     * @return
     */
    @GetMapping("webclient")
    public Mono<String> httpget() {
        return WebClient.create("http://localhost:8080").get()
                .uri("/webflux")
                .retrieve()
                .bodyToMono(String.class);
    }

    /**
     * 服务器推送事件（Server-Sent Events，SSE）允许服务器端不断地推送数据到客户端。
     * 相对于 WebSocket 而言，服务器推送事件只支持服务器端到客户端的单向数据传递。
     * @return
     */
    @GetMapping("/random")
    public Flux<ServerSentEvent<Integer>> randomNumbers() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
                .map(data -> ServerSentEvent.<Integer>builder()
                        .event("random")
                        .id(Long.toString(data.getT1()))
                        .data(data.getT2())
                        .build());
    }
}
