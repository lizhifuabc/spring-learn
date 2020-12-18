package com.boot.rabbitmq.controller;

import com.mq.common.message.MessageObject;
import com.mq.common.constant.RabbitMqConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Direct
 *
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
