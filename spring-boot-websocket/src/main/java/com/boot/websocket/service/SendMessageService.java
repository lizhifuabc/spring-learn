package com.boot.websocket.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * SendMessageService
 *
 * @author lizhifu
 * @date 2021/3/30
 */
@Service
@Slf4j
public class SendMessageService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    /**
     * redis发布消息，就是往指定频道发消息
     *
     * @param channel 订阅的频道
     * @param message 发布 的内容
     */
    public void sendMessage(String channel, String message) {
        stringRedisTemplate.convertAndSend(channel, message);
        log.info("您已经向频道：" + channel + "发布了一条消息，内容是： " + message);
    }
}
