package com.mq.delay.mq;

import com.mq.delay.config.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * MqSender
 *
 * @author lizhifu
 * @date 2021/4/9
 */
@Service
@Slf4j
public class MqSender {
    @Resource
    private RabbitTemplate rabbitTemplate;
    /**
     * confirmCallback
     */
   private final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            log.info("correlationData: " + correlationData);
            log.info("ack: " + ack);
            if(!ack){
                log.info("异常....");
            }
        }
    };
    /**
     * returnCallback
     */
    private final RabbitTemplate.ReturnsCallback returnCallback = new RabbitTemplate.ReturnsCallback() {
        @Override
        public void returnedMessage(ReturnedMessage returnedMessage) {
            log.info("returnedMessage: " + returnedMessage.toString());
        }
    };

    /**
     * 发送延迟消息
     * @param message
     */
    public void sendDelay(Object message,Integer delay){
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnsCallback(returnCallback);
        //id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData("12345678909"+ new Date());

        //发送消息时指定 header 延迟时间
        rabbitTemplate.convertAndSend(RabbitmqConfig.DELAY_EXCHANGE, "Delay.boot", message,
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        //设置消息持久化
                        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        //message.getMessageProperties().setHeader("x-delay", "6000");
                        message.getMessageProperties().setDelay(delay);
                        return message;
                    }
                }, correlationData);
    }
}
