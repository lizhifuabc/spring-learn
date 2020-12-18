package com.boot.mqconsume.listener;

import com.mq.common.message.MessageObject;
import com.rabbitmq.client.Channel;
import com.mq.common.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;

import java.io.IOException;

/**
 * direct 监听
 *
 * @author lizhifu
 * @date 2020/12/17
 */
//监听指定的队列名称
@RabbitListener(queues = RabbitMqConstant.DIRECT_QUEUE)
@Slf4j
@Component
public class DirectQueueListener {
    /**
     * 如果 spring.rabbitmq.listener.direct.acknowledge-mode: auto，则可以用这个方式，会自动ack
     */
    // @RabbitHandler
    public void autoAck(MessageObject msg) {
        log.info("DirectQueueListener，接收消息：{}", msg.getMessage());
    }
    @RabbitHandler
    public void process(MessageObject msg, Channel channel, Message message){
        log.info("DirectQueueListener，接收消息：{}", msg.getMessage());
        //  如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            //告诉服务器收到这条消息已经被当前消费者消费了，可以在队列安全删除，这样后面就不会再重发了，
            //否则消息服务器以为这条消息没处理掉，后续还会再发
            //第二个参数是消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
            channel.basicAck(deliveryTag, false);
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
