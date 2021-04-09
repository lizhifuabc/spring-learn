package com.mq.delay.mq;

import com.mq.delay.config.RabbitmqConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * MqReceiver消费者
 *
 * @author lizhifu
 * @date 2021/4/9
 */
@Service
@Slf4j
public class MqReceiver {
    @RabbitListener(queues = RabbitmqConfig.DELAY_QUEUE)
    @RabbitHandler
    public void onDelayMessage(Message message, Channel channel) throws IOException {
        //  如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        log.info("delay receive " + deliveryTag);
        //告诉服务器收到这条消息已经被当前消费者消费了，可以在队列安全删除，这样后面就不会再重发了，
        //否则消息服务器以为这条消息没处理掉，后续还会再发
        //第二个参数是消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
        channel.basicAck(deliveryTag, false);
        log.info("delay receive " + new String(message.getBody()));
    }
}
