package com.boot.kafka.controller;

import com.boot.kafka.kafka.KafkaProducer;
import com.mq.common.message.MessageObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Kafka
 *
 * @author lizhifu
 * @date 2021/1/4
 */
@RestController
public class KafkaController {
    @Resource
    private KafkaProducer kafkaProducer;
    @GetMapping("/sendMsg")
    public String sendMsg (){
        MessageObject messageObject = new MessageObject(UUID.randomUUID().toString());
        kafkaProducer.send(messageObject);
        return "信息发送成功";
    }

}
