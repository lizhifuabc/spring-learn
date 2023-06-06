package com.boot.rabbitmq.controller;

import com.mq.common.constant.RabbitMqConstant;
import com.mq.common.message.MessageObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * Fanout: 广播模式
 *
 * @author lizhifu
 * @date 2020/12/17
 */
@RestController
public class RabbitMqFanoutController {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @GetMapping("/sendFanout")
    public String sendDirect() {
        rabbitTemplate.convertAndSend(RabbitMqConstant.FANOUT_MODE_EX, "",new MessageObject("Fanout message"));
        return "发送成功";
    }
}
