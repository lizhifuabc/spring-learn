package com.boot.websocket.handler;

import com.boot.websocket.session.WsSessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.time.LocalDateTime;

/**
 * ws消息处理
 *
 * @author lizhifu
 * @since  2021/3/29
 */
@Component
@Slf4j
public class WsHandler extends AbstractWebSocketHandler {
    /**
     * 方法是在 socket 连接成功后被触发，同原生注解里的 @OnOpen 功能。
     * @param session session
     * @throws Exception Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = session.getAttributes().get("userId").toString();
        log.info("建立ws连接{} userId {}",session.getId(),userId);
        WsSessionManager.add(userId,session);
        super.afterConnectionEstablished(session);
    }
    /**
     * 方法是在客户端发送普通文件信息时触发，同原生注解里的 @OnMessage 功能。
     * @param session session
     * @param message message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String userId = session.getAttributes().get("userId").toString();
        log.info("服务器收到消息userId{}",userId);
        // 获得客户端传来的消息
        String payload = message.getPayload();
        log.info("server 接收到消息 " + payload);
        session.sendMessage(new TextMessage("server 发送给的消息 " + payload + "，发送时间:" + LocalDateTime.now().toString()));
    }
    /**
     * 方法同原生注解里的@OnError
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        String userId = session.getAttributes().get("userId").toString();
        log.error("异常处理userId{}",userId);
        WsSessionManager.removeClose(userId);
    }

    /**
     * 方法是在 socket 连接关闭后被触发，同原生注解里的 @OnClose 功能
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = session.getAttributes().get("userId").toString();
        log.info("关闭ws连接userId{}",userId);
        WsSessionManager.removeClose(userId);
    }
}
