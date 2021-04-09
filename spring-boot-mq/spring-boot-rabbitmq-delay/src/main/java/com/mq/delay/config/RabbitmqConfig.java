package com.mq.delay.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMqConfig
 *
 * @author lizhifu
 * @date 2021/4/9
 */
@Configuration
public class RabbitmqConfig {
    public static final String DELAY_EXCHANGE = "Ex.DelayExchange";
    public static final String DELAY_QUEUE = "MQ.DelayQueue";
    public static final String DELAY_KEY = "Delay.#";
    @Bean
    public TopicExchange delayExchange(){
        TopicExchange exchange = new TopicExchange(DELAY_EXCHANGE, true, false);
        exchange.setDelayed(true);
        return exchange;
    }
//    @Bean
    public TopicExchange delayExchange2(){
        Map<String, Object> pros = new HashMap<>();
        // 设置交换机支持延迟消息推送
        //主题路由匹配交换机
        pros.put("x-delayed-message", "topic");
        //直接连接交换机
        //pros.put("x-delayed-message", "direct");
        TopicExchange exchange = new TopicExchange(DELAY_EXCHANGE, true, false,pros);
        return exchange;
    }
    /**
     * 延迟队列
     * @return
     */
    @Bean
    public Queue delayQueue(){
        return new Queue(DELAY_QUEUE, true);
    }

    /**
     * 延迟队列绑定自定义交换器
     * @param delayExchange
     * @param delayQueue
     * @return
     */
    @Bean
    public Binding delayBinding(TopicExchange delayExchange,Queue delayQueue){
        return BindingBuilder.bind(delayQueue).to(delayExchange).with(DELAY_KEY);
    }
}
