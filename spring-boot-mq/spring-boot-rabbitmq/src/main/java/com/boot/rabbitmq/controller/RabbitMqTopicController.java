package com.boot.rabbitmq.controller;

import com.mq.common.constant.RabbitMqConstant;
import com.mq.common.message.MessageObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Topic: 主题模式
 *
 * @author lizhifu
 * @date 2020/12/17
 */
@RestController
public class RabbitMqTopicController {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @GetMapping("/sendTopic")
    public String sendTopic() {
        rabbitTemplate.convertAndSend(RabbitMqConstant.TOPIC_MODE_EX, "queue.aaa.bbb",new MessageObject("Fanout message"));
        rabbitTemplate.convertAndSend(RabbitMqConstant.TOPIC_MODE_EX, "ccc.queue",new MessageObject("Fanout message"));
        rabbitTemplate.convertAndSend(RabbitMqConstant.TOPIC_MODE_EX, "3.queue",new MessageObject("Fanout message"));
        return "发送成功";
    }
}
