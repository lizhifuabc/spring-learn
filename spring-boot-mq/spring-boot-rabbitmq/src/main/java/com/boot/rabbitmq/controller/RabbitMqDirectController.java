package com.boot.rabbitmq.controller;

import com.mq.common.message.MessageObject;
import com.mq.common.constant.RabbitMqConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * Direct
 * 创建 queue 的时候将其设置为持久化
 * 发送消息设置发送模式deliveryMode=2代表持久化消息:默认
 * 关闭自动ack，使用手动ack
 * @author lizhifu
 * @date 2020/12/17
 */
@RestController
public class RabbitMqDirectController {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @GetMapping("/sendDirect")
    public String sendDirect() {
        rabbitTemplate.convertAndSend(RabbitMqConstant.DIRECT_QUEUE, new MessageObject("direct message"));
        return "发送成功";
    }
}
