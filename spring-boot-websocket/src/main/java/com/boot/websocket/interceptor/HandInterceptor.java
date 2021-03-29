package com.boot.websocket.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.time.LocalDate;
import java.util.Map;

/**
 * 握手拦截
 *
 * @author lizhifu
 * @date 2021/3/29
 */
@Component
@Slf4j
public class HandInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        log.info("握手开始"+ LocalDate.now());
        // 获得请求参数
        String path = serverHttpRequest.getURI().getPath();
        String[] ss = StringUtils.split(path, "/");
        map.put("userId", ss[1].split("/")[1]);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        log.info("握手完成"+ LocalDate.now());
    }
}
