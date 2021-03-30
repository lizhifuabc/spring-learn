package com.boot.websocket.service;

import com.boot.websocket.session.WsSessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * WsService
 *
 * @author lizhifu
 * @date 2021/3/29
 */
@Service
@Slf4j
public class WsService {
    /**
     * 发送消息,如果连接断开，缓存到数据库
     * @param session
     * @param text
     * @return
     * @throws IOException
     */
    public void sendMsg(WebSocketSession session, String text) throws IOException {
        String userId = session.getAttributes().get("userId").toString();
        if (!isConnected(userId)) {
            log.info("Send failure, connection [" + userId + "] interrupt ");
            return;
        }
        session.sendMessage(new TextMessage(text));
    }
    /**
     * 发送消息,如果连接断开，缓存到数据库
     * @param toUserId
     * @param text
     * @return
     * @throws IOException
     */
    public void sendMsg(String toUserId, String text) throws IOException {
        if (!isConnected(toUserId)) {
            log.info("Send failure, connection [" + toUserId + "] interrupt ");
            return;
        }
        WsSessionManager.get(toUserId).sendMessage(new TextMessage(text));
    }

    /**
     * 广播消息
     * @param text
     * @return
     * @throws IOException
     */
    public void broadcastMsg(String text) throws IOException {
        for (WebSocketSession session: WsSessionManager.WS_SESSION_POOL.values()) {
            session.sendMessage(new TextMessage(text));
        }
    }
    /**
     * 查询客户端是否在线
     * @param key	客户端ID
     * @return
     */
    public boolean isConnected(String key){
        if (WsSessionManager.WS_SESSION_POOL.containsKey(key)) {
            WebSocketSession session = WsSessionManager.WS_SESSION_POOL.get(key);
            if(null != session && session.isOpen()) {
                return true;
            }
            log.info("connection[" + key + "] closed");
        }else {
            log.info("connection[" + key + "] not found");
        }
        return false;
    }
}
