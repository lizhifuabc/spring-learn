package com.boot.rabbitmq.config;

import com.mq.common.constant.RabbitMqConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 延迟队列:首先，它是一种队列，队列意味着内部的元素是有序的，元素出队和入队是有方向性的，元素从一端进入，从另一端取出。
 *
 * @author lizhifu
 * @date 2020/12/17
 */
@Configuration
public class DelayRabbitMqConfig {
    /**
     * 延迟队列
     */
    @Bean
    public Queue delayQueue() {
        return new Queue(RabbitMqConstant.DELAY_QUEUE, true);
    }

    /**
     * 延迟队列交换器, x-delayed-type 和 x-delayed-message 固定
     */
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(RabbitMqConstant.DELAY_MODE_EX, "x-delayed-message", true, false, args);
    }
    /**
     * 延迟队列绑定自定义交换器
     *
     * @param delayQueue    队列
     * @param delayExchange 延迟交换器
     */
    @Bean
    public Binding delayBinding(Queue delayQueue, CustomExchange delayExchange) {
        return BindingBuilder.bind(delayQueue).to(delayExchange).with(RabbitMqConstant.DELAY_QUEUE).noargs();
    }
}
