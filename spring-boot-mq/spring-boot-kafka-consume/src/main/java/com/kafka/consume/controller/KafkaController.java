package com.kafka.consume.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.kafka.config.KafkaListenerEndpointRegistry;


import java.util.Objects;

/**
 * Kafka Controller:手动操控kafka的队列监听情况
 *
 * @author lizhifu
 * @since 2023/7/25
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    public KafkaController(KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry) {
        this.kafkaListenerEndpointRegistry = kafkaListenerEndpointRegistry;
    }

    /**
     * 下面的方法可以手动操控kafka的队列监听情况
     * 先发送一条消息，因为autoStartup = "false"，所以并不会看到有消息进入监听器。
     * 接着启动监听器，/start/webGroup。可以看到有一条消息进来了。
     * pause是暂停监听，resume是继续监听
     *
     * @param listenerId consumer的group-id
     */
    @RequestMapping("/pause/{listenerId}")
    public void stop(@PathVariable String listenerId) {
        Objects.requireNonNull(kafkaListenerEndpointRegistry.getListenerContainer(listenerId)).pause();
    }

    @RequestMapping("/resume/{listenerId}")
    public void resume(@PathVariable String listenerId) {
        Objects.requireNonNull(kafkaListenerEndpointRegistry.getListenerContainer(listenerId)).resume();
    }

    @RequestMapping("/start/{listenerId}")
    public void start(@PathVariable String listenerId) {
        Objects.requireNonNull(kafkaListenerEndpointRegistry.getListenerContainer(listenerId)).start();
    }
}
