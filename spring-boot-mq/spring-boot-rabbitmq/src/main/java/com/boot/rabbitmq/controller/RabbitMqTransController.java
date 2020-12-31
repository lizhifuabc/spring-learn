package com.boot.rabbitmq.controller;

import com.boot.rabbitmq.trans.TransactionSender;
import com.mq.common.constant.RabbitMqConstant;
import com.mq.common.message.MessageObject;
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
public class RabbitMqTransController {
    @Resource
    private TransactionSender transactionSender;
    @GetMapping("/trans")
    public String sendDirect() {
        transactionSender.send("RabbitMqConstant.DIRECT_QUEUE", new MessageObject("trans message"));
        return "发送成功";
    }
}
