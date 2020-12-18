package com.boot.rabbitmq.controller;

import com.mq.common.constant.RabbitMqConstant;
import com.mq.common.message.MessageObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 死信队列
 *
 * @author lizhifu
 * @date 2020/12/17
 */
@RestController
public class RabbitMqDeadController {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @GetMapping("/sendDead")
    public String sendDead() {
        rabbitTemplate.convertAndSend(RabbitMqConstant.BUSINESS_TOPIC_EX, "normal.测试",new MessageObject("死信队列"));
//        rabbitTemplate.convertAndSend(RabbitMqConstant.DLX_EXCHANGE, "dlx.死信队列接收队列测试",new MessageObject("死信队列接收队列"));
        return "发送成功";
    }
}
