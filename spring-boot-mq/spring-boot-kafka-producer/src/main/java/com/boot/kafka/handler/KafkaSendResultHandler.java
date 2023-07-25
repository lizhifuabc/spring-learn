package com.boot.kafka.handler;

import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

/**
 * kafka消息发送回调
 *
 * @author lizhifu
 * @since 2023/7/25
 */
@Component
@Slf4j
public class KafkaSendResultHandler implements ProducerListener<Object, Object> {
    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        log.info("消息发送成功：" + producerRecord);
    }

    @Override
    public void onError(ProducerRecord producerRecord, @Nullable RecordMetadata recordMetadata, Exception exception) {
        log.error("消息发送失败：" + producerRecord.toString() + exception.getMessage());
    }
}
