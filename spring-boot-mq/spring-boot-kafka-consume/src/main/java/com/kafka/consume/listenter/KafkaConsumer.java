package com.kafka.consume.listenter;

import com.mq.common.constant.KafkaConstant;
import com.mq.common.message.MessageObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * Consumer
 *
 * @author lizhifu
 * @since  2021/1/4
 */
@Slf4j
@Component
public class KafkaConsumer {
    /**
     * 监听kafka消息
     *
     * @param consumerRecord kafka的消息，用consumerRecord可以接收到更详细的信息，也可以用String message只接收消息
     * @param ack  kafka的消息确认
     * 使用autoStartup = "false"必须指定id
     */
    @KafkaListener(topics = {"topic1", "topic2"}, errorHandler = "ownKafkaListenerErrorHandler")
    // @KafkaListener(id = "${spring.kafka.consumer.group-id}", topics = {"topic1", "topic2"}, autoStartup = "false")
    public void consumer(ConsumerRecord<Object, Object> consumerRecord, Acknowledgment ack) {
        try {
            //用于测试异常处理
            //int i = 1 / 0;
            log.info("消费消息：{}", consumerRecord.value());
            //手动确认
            ack.acknowledge();
        } catch (Exception e) {
            // 消费失败，记录日志
            // 最好也要确认消息
            ack.acknowledge();
            log.error("消费失败：{}", consumerRecord.value(),e);
        }
    }
}
