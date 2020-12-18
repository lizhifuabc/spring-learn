package com.boot.rabbitmq.config;

import com.mq.common.constant.RabbitMqConstant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 死信队列
 * 消息被拒绝（basic.reject或basic.nack）并且requeue=false.
 * 消息TTL过期
 * 队列达到最大长度（队列满了，无法再添加数据到mq中）
 *
 * 在定义业务队列的时候，要考虑指定一个死信交换机，死信交换机可以和任何一个普通的队列进行绑定，然后在业务队列出现死信的时候就会将数据发送到死信队列。
 * 死信队列实际上就是一个普通的队列，只是这个队列跟死信交换机进行了绑定，用来存放死信而已。
 *
 * #####定义业务（普通）队列的时候指定参数
 *
 * x-dead-letter-exchange: 用来设置死信后发送的交换机
 * x-dead-letter-routing-key：用来设置死信的routingKey
 *
 * @author lizhifu
 * @date 2020/12/17
 */
@Configuration
public class DeadRabbitMqConfig {
    /**
     * 正常业务的Queue
     * 因为该队列需要绑定死信交换机，
     * 所以需要加俩参数：死信交换机:x-dead-letter-exchange，死信消息路由键:x-dead-letter-routing-key）
     * @return
     */
    @Bean
    public Queue businessQuery() {
        Map<String, Object> args = new HashMap<String, Object>();
        // 绑定死信交换器
        args.put("x-dead-letter-exchange", RabbitMqConstant.DLX_EXCHANGE);
        // 绑定死信路由键
        args.put("x-dead-letter-routing-key","dlx");
        // 设置队列失效时间 4000ms
//        args.put("x-message-ttl", 4000);
        return new Queue(RabbitMqConstant.BUSINESS_QUERY,true, false, false, args);
    }
    /**
     * 创建一个常用的topic 交换器 用户接收 消息
     */
    @Bean
    public TopicExchange businessExchange() {
        return new TopicExchange(RabbitMqConstant.BUSINESS_TOPIC_EX);
    }

    @Bean
    public Binding bingBusinessQueue(Queue businessQuery, TopicExchange businessExchange) {
        return BindingBuilder.bind(businessQuery).to(businessExchange).with("normal.#");
    }
    /**
     * 死信消息队列
     */
    @Bean
    public Queue acceptQueue() {
        return new Queue(RabbitMqConstant.BUSINESS_QUERY_DEAD,true);
    }

    /**
     * 死信交换器
     */
    @Bean
    public TopicExchange dlxExchange() {
        return new TopicExchange(RabbitMqConstant.DLX_EXCHANGE);
    }
    /**
     * 死信队列绑定
     * @return
     */
    @Bean
    public Binding bingAcceptQueue(TopicExchange dlxExchange,Queue acceptQueue) {
        return BindingBuilder.bind(acceptQueue).to(dlxExchange).with("dlx");
    }
}
