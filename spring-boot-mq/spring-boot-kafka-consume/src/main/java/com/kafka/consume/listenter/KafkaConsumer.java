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
 * @date 2021/1/4
 */
@Slf4j
@Component
public class KafkaConsumer {
    @KafkaListener(topics = KafkaConstant.TOPIC_TEST)
    public void consumer(ConsumerRecord record, Acknowledgment acknowledgment) {
        try {
            String message = (String) record.value();
            log.info("收到消息: {}", message.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }
}
