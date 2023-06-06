package com.boot.rabbitmq.trans;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

/**
 * TransactionSender
 * RabbitMQ 事务机制（同步），基本上吞吐量会下来，因为太耗性能。
 * @author lizhifu
 * @date 2020/12/31
 */
@Component
public class TransactionSender {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Transactional(rollbackFor = Exception.class)
    public void send(String routingKey, Object object) {
        // 开启事务模式 如果开启事务 需要关闭spring.rabbitmq.publisher-confirm-type=NONE
        rabbitTemplate.setChannelTransacted(true);
        rabbitTemplate.convertAndSend(routingKey, object);
        //测试消息是否会回滚 此时消费者是无法获取到消息的
        throw new RuntimeException("测试");
    }
}
