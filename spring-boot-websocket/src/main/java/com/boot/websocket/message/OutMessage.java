package com.boot.websocket.message;

import java.time.LocalDateTime;

/**
 * 发送的消息
 *
 * @author lizhifu
 * @date 2021/3/30
 */
public class OutMessage {
    /**
     * 前端拿到发送的可以做一些事
     */
    private String from;
    /**
     * 内容
     */
    private String content;
    /**
     * 时间
     */
    private LocalDateTime time = LocalDateTime.now();
}
