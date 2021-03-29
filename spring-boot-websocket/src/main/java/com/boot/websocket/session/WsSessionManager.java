package com.boot.websocket.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * session管理
 *
 * @author lizhifu
 * @date 2021/3/29
 */
@Slf4j
public class WsSessionManager {
    /**
     * 本地保存WebSocketSession
     */
    public static ConcurrentHashMap<String, WebSocketSession> WS_SESSION_POOL = new ConcurrentHashMap<>();

    /**
     * 添加 session
     * @param key
     */
    public static void add(String key, WebSocketSession session) {
        WS_SESSION_POOL.put(key, session);
    }
    /**
     * 删除并返回session
     * @param key
     * @return
     */
    public static WebSocketSession remove(String key) {
        return WS_SESSION_POOL.remove(key);
    }
    /**
     * 删除并同步关闭连接
     *
     * @param key
     */
    public static void removeClose(String key) {
        WebSocketSession session = remove(key);
        if (session != null) {
            try {
                session.close();
            } catch (IOException e) {
                log.error("removeClose IOException",e);
            }
        }
    }
    /**
     * 获得 session
     * @param key
     * @return
     */
    public static WebSocketSession get(String key) {
        return WS_SESSION_POOL.get(key);
    }
}
