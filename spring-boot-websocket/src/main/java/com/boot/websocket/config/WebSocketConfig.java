package com.boot.websocket.config;

import com.boot.websocket.handler.WsHandler;
import com.boot.websocket.interceptor.HandInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * WebSocketConfig 配置
 * 提供对应的onOpen、onClose、onMessage、onError方法
 * @author lizhifu
 * @date 2021/3/29
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Resource
    private WsHandler wsHandler;
    @Resource
    private HandInterceptor handInterceptor;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                //注册 handler,这里的 url 要与页面的 url 一致
                .addHandler(wsHandler,"wsHandler/{userId}")
                //握手拦截器
                .addInterceptors(handInterceptor)
                //跨域
                .setAllowedOrigins("*");
    }
}
