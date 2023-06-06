package com.boot.rabbitmq.controller;

import com.mq.common.constant.RabbitMqConstant;
import com.mq.common.message.MessageObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * 延迟队列
 *
 * @author lizhifu
 * @date 2020/12/17
 */
@RestController
public class RabbitMqDelayController {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @GetMapping("/sendDelay")
    public String sendDelay() {
        rabbitTemplate.convertAndSend(RabbitMqConstant.DELAY_MODE_EX, RabbitMqConstant.DELAY_QUEUE, new MessageObject("延迟消息5s"), message -> {
            message.getMessageProperties().setHeader("x-delay", 5000);
            return message;
        });
        return "发送成功";
    }
}
