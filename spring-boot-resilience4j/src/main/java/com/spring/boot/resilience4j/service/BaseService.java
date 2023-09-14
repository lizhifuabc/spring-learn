package com.spring.boot.resilience4j.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/**
 * 基础服务
 *
 * @author lizhifu
 * @since 2023/9/11
 */
public interface BaseService {
    /**
     * failure:失败
     * @return String
     */
    String failure();
    /**
     * failureWithFallback:失败 Fallback
     * @return String
     */
    String failureWithFallback();
    /**
     * success:成功
     * @return String
     */
    String success();
    /**
     * successException:成功 异常
     * @return String
     */
    String successException();
    /**
     * ignoreException:忽略异常
     * 测试忽略异常，不会触发熔断
     * @return String
     */
    String ignoreException();
    /**
     * fluxSuccess:Flux成功
     * @return Flux
     */
    Flux<String> fluxSuccess();
    /**
     * fluxFailure:Flux失败
     * @return Flux
     */
    Flux<String> fluxFailure();
    /**
     * fluxTimeout:Flux超时
     * @return Flux
     */
    Flux<String> fluxTimeout();
    /**
     * monoSuccess:Mono成功
     * @return Mono
     */
    Mono<String> monoSuccess();
    /**
     * monoFailure:Mono失败
     * @return Mono
     */
    Mono<String> monoFailure();
    /**
     * monoTimeout:Mono超时
     * @return Mono
     */
    Mono<String> monoTimeout();
    /**
     * futureSuccess:Future成功
     * @return CompletableFuture
     */
    CompletableFuture<String> futureSuccess();
    /**
     * futureFailure:Future失败
     * @return CompletableFuture
     */
    CompletableFuture<String> futureFailure();
    /**
     * futureTimeout:Future超时
     * @return CompletableFuture
     */
    CompletableFuture<String> futureTimeout();
}
