package com.boot.kafka.controller;

import com.boot.kafka.domain.User;
import com.boot.kafka.handler.KafkaSendResultHandler;
import com.boot.kafka.kafka.KafkaProducer;
import com.mq.common.message.MessageObject;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Kafka
 *
 * @author lizhifu
 * @since  2021/1/4
 */
@RestController
//这个注解代表这个类开启Springboot事务，因为我们在Kafka的配置文件开启了Kafka事务，不然会报错
@Transactional(rollbackFor = RuntimeException.class)
public class KafkaController {
    private final KafkaTemplate<Object, Object> kafkaTemplate;

    @Resource
    private KafkaProducer kafkaProducer;

    public KafkaController(KafkaTemplate<Object, Object> kafkaTemplate, KafkaSendResultHandler kafkaSendResultHandler){
        this.kafkaTemplate = kafkaTemplate;
        //回调方法、异常处理
        this.kafkaTemplate.setProducerListener(kafkaSendResultHandler);
    }
    @RequestMapping("/sendMultiple")
    public void sendMultiple() {
        String message = "发送到Kafka的消息";
        for (int i = 0;i < 10;i++) {
            kafkaTemplate.send("topic1", "发送到Kafka的消息" + i);
            System.out.println(message + i);
        }
    }
    @RequestMapping("/send")
    public void send() {
        User user = new User(1, "李志福");
        kafkaTemplate.send("topic1", user);
        kafkaTemplate.send("topic2", "发给topic2");
    }
    /**
     * Kafka提供了多种构建消息的方式
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws TimeoutException
     */
    public void SendDemo() throws ExecutionException, InterruptedException, TimeoutException {
        //后面的get代表同步发送，括号内时间可选，代表超过这个时间会抛出超时异常，但是仍会发送成功
        kafkaTemplate.send("topic1", "发给topic1").get(1, TimeUnit.MILLISECONDS);

        //使用ProducerRecord发送消息
        ProducerRecord<Object, Object> producerRecord = new ProducerRecord<>("topic.quick.demo", "use ProducerRecord to send message");
        kafkaTemplate.send(producerRecord);

        //使用Message发送消息
        Map<String, Object> map = new HashMap<>();
        map.put(KafkaHeaders.TOPIC, "topic.quick.demo");
//        map.put(KafkaHeaders.PARTITION_ID, 0);
//        map.put(KafkaHeaders.MESSAGE_KEY, 0);
        GenericMessage<Object> message = new GenericMessage<>("use Message to send message", new MessageHeaders(map));
        kafkaTemplate.send(message);
    }
    @GetMapping("/sendMsg")
    public String sendMsg (){
        MessageObject messageObject = new MessageObject(UUID.randomUUID().toString());
        kafkaProducer.send(messageObject);
        return "信息发送成功";
    }

}
