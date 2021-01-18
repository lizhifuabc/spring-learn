package com.demo.webflux.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * handler
 *
 * @author lizhifu
 * @date 2021/1/15
 */
@Component
public class HelloWorldHandler {
    public Mono<ServerResponse> say(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("HelloWorldHandler SpringWebFlux"));
    }
}
