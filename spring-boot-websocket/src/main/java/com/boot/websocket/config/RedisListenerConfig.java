package com.boot.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * redis监听配置
 *
 * @author lizhifu
 * @date 2021/3/29
 */
@Configuration
public class RedisListenerConfig {
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,MessageListenerAdapter messageListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //这里要设定监听的主题是chat
        container.addMessageListener(messageListenerAdapter, new PatternTopic(RedisKey.REDIS_MQ_CHAT));

        return container;
    }
    /**
     * 消息监听适配器，注入接受消息方法
     *
     * @param receiver
     * @return
     */
    @Bean
    public MessageListenerAdapter messageListenerAdapter(ChatMessageListener receiver) {
        return new MessageListenerAdapter(receiver);
    }
}
