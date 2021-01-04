package com.boot.kafka.kafka;

import com.mq.common.constant.KafkaConstant;
import com.mq.common.message.MessageObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * KafkaProducer
 *
 * @author lizhifu
 * @date 2021/1/4
 */
@Component
@Slf4j
public class KafkaProducer {
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;
    public void send(MessageObject messageObject){
        log.info("KafkaProducer 发送消息为{}",messageObject.toString());
        // 这里Topic如果不存在，会自动创建
        kafkaTemplate.send(KafkaConstant.TOPIC_TEST,messageObject.toString());
    }
}
