package com.boot.mqconsume.listener;

import com.mq.common.constant.RabbitMqConstant;
import com.mq.common.message.MessageObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Fanout 监听
 *
 * @author lizhifu
 * @date 2020/12/17
 */
//监听指定的队列名称
@RabbitListener(queues = RabbitMqConstant.BUSINESS_QUERY)
@Slf4j
@Component
public class DeadQueueListener {
    /**
     * 如果 spring.rabbitmq.listener.direct.acknowledge-mode: auto，则可以用这个方式，会自动ack
     */
    // @RabbitHandler
    public void autoAck(MessageObject msg) {
        log.info("DeadQueueListener，接收消息：{}", msg.getMessage());
    }
    @RabbitHandler
    public void process(MessageObject msg, Channel channel, Message message){
        //  如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        try {
            //BasicReject方法第一个参数是消息的DeliveryTag，对于每个Channel来说，
            // 每个消息都会有一个DeliveryTag，一般用接收消息的顺序来表示：1,2,3,4 等等。第二个参数是是否放回queue中，requeue。
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            log.info("DeadQueueListener，接收消息：{}{}", msg.getMessage(),message.getMessageProperties().getDeliveryTag());
        } catch (Exception e) {
            try {
                // 处理失败,重新压入MQ
                channel.basicRecover();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
