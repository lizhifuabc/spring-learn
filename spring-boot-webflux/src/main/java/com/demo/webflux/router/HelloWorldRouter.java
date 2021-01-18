package com.demo.webflux.router;

import com.demo.webflux.handler.HelloWorldHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * HelloWorldRouter
 *
 * @author lizhifu
 * @date 2021/1/15
 */
@Configuration
public class HelloWorldRouter {
    @Bean
    public RouterFunction<ServerResponse> routeHi(HelloWorldHandler helloWorldHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/helloWorld")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        helloWorldHandler::say);
    }
}
