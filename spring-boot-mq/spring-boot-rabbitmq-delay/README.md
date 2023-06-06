---
title: 延迟队列-Rabbitmq
date: 2021-04-09 13:21:29
tags: 
	- Rabbitmq
	- 队列
	- Java
	- SpringBoot
categories: 
	- SpringBoot
---

在 RabbitMQ 3.6.x 之前一般采用死信队列+TTL过期时间的方式来实现延迟队列。但是在 RabbitMQ 3.6.x 开始，RabbitMQ 官方提供了延迟队列的插件，可以下载放置到 RabbitMQ 根目录下的 plugins 下。

地址：https://github.com/rabbitmq/rabbitmq-delayed-message-exchange

本文章源码位置：https://github.com/lizhifuabc/spring-learn/tree/main/spring-boot-mq/spring-boot-rabbitmq-delay

<!--more-->

# 延迟队列

延迟队列声明方式：

```java
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
```

> delayExchange() delayExchange2()，两种方式设置延迟队列
>
> - exchange.setDelayed(true);
> - pros.put("x-delayed-message", "topic");

# 生产者

MqSender:

```java
package com.mq.delay.mq;

import com.mq.delay.config.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Date;

/**
 * MqSender
 *
 * @author lizhifu
 * @date 2021/4/9
 */
@Service
@Slf4j
public class MqSender {
    @Resource
    private RabbitTemplate rabbitTemplate;
    /**
     * confirmCallback
     */
   private final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            log.info("correlationData: " + correlationData);
            log.info("ack: " + ack);
            if(!ack){
                log.info("异常....");
            }
        }
    };
    /**
     * returnCallback
     */
    private final RabbitTemplate.ReturnsCallback returnCallback = new RabbitTemplate.ReturnsCallback() {
        @Override
        public void returnedMessage(ReturnedMessage returnedMessage) {
            log.info("returnedMessage: " + returnedMessage.toString());
        }
    };

    /**
     * 发送延迟消息
     * @param message
     */
    public void sendDelay(Object message){
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnsCallback(returnCallback);
        //id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData("12345678909"+ new Date());

        //发送消息时指定 header 延迟时间
        rabbitTemplate.convertAndSend(RabbitmqConfig.DELAY_EXCHANGE, "Delay.boot", message,
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        //设置消息持久化
                        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        //message.getMessageProperties().setHeader("x-delay", "6000");
                        message.getMessageProperties().setDelay(6000);
                        return message;
                    }
                }, correlationData);
    }
}
```

# 消费者

MqReceiver:

```java
package com.mq.delay.mq;

import com.mq.delay.config.RabbitmqConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * MqReceiver消费者
 *
 * @author lizhifu
 * @date 2021/4/9
 */
@Service
@Slf4j
public class MqReceiver {
    @RabbitListener(queues = RabbitmqConfig.DELAY_QUEUE)
    @RabbitHandler
    public void onDelayMessage(Message message, Channel channel) throws IOException {
        //  如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        log.info("delay receive " + deliveryTag);
        //告诉服务器收到这条消息已经被当前消费者消费了，可以在队列安全删除，这样后面就不会再重发了，
        //否则消息服务器以为这条消息没处理掉，后续还会再发
        //第二个参数是消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
        channel.basicAck(deliveryTag, false);
        log.info("delay receive " + new String(message.getBody()));
    }
}
```

# 测试

MqSenderTest:

```java
package com.mq.delay;

import com.mq.delay.mq.MqSender;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;

/**
 * MqSenderTest
 *
 * @author lizhifu
 * @date 2021/4/9
 */
@SpringBootTest
public class MqSenderTest {
    @Resource
    MqSender mqSender;
    @Test
    public void test(){
        mqSender.sendDelay("MqSenderTest");
    }
}
```

