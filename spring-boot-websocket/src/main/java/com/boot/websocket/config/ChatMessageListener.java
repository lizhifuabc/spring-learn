package com.boot.websocket.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 聊天消息监听器
 *
 * @author lizhifu
 * @date 2021/3/30
 */
@Component
@Slf4j
public class ChatMessageListener implements MessageListener {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public void onMessage(Message message, byte[] bytes) {
        RedisSerializer<String> valueSerializer = stringRedisTemplate.getStringSerializer();
        String value = valueSerializer.deserialize(message.getBody());
        log.info("监听集群websocket消息--- {}", value);

    }
}
