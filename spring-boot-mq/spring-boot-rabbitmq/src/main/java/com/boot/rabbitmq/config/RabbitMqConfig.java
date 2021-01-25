package com.boot.rabbitmq.config;

import com.mq.common.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;


/**
 * RabbitMqConfig 配置
 *
 * @author lizhifu
 * @date 2020/12/17
 */
@Configuration
@Slf4j
public class RabbitMqConfig {
    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //交换器无法根据自身类型和路由键找到一个符合条件的队列时的处理方式
        //true：RabbitMQ会调用Basic.Return命令将消息返回给生产者
        //false：RabbitMQ会把消息直接丢弃
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause)
                -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        rabbitTemplate.setReturnsCallback((message)
                -> log.info("消息发送失败:exchange({}),route({}),replyCode({}),replyText({}),message:{}",
                message.getExchange(), message.getRoutingKey(), message.getReplyCode(), message.getReplyText(), message.getMessage()));
        return rabbitTemplate;
    }
    //----------------------点对点 direct 模式-----------------------------------------------------------------------------------
    /**
     * Direct：全匹配式传递。当RoutingKey和消息标志完全一样才会存放到对应的队列
     * name 队列名
     * durable 是否是持久化队列（服务器重启不会消失）默认为true
     * exclusive 普通队列允许的消费者没有限制，多个消费者绑定到多个队列时，RabbitMQ 会采用轮询进行投递。如果需要消费者独占队列，在队列创建的时候，设定属性 exclusive 为 true。
     * autoDelete 是否自动删除
     * map 参数传递
     * @return Queue
     */
    @Bean
    public Queue queue() {
        return new Queue(RabbitMqConstant.DIRECT_QUEUE);
    }
    //------------------------------------------------end-----------------------------------------------------------------------------------

    //----------------------fanout(广播模式)模式:简单的讲，就是把交换机（Exchange）里的消息发送给所有绑定该交换机的队列，忽略routingKey-------
    /**
     * 声明 并 加载广播队列
     */
    @Bean
    public Queue fanoutQueue() {
        return new Queue(RabbitMqConstant.FANOUT_QUEUE);
    }
    /**
     * 声明 并 加载广播队列2
     */
    @Bean
    public Queue fanoutQueue2() {
        return new Queue(RabbitMqConstant.FANOUT_QUEUE2);
    }
    /**
     * 声明 并 加载广播交换器
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitMqConstant.FANOUT_MODE_EX);
    }
    /**
     * 将 广播队列 和 广播交换器 绑定
     *
     * @param fanoutQueue    广播队列
     * @param fanoutExchange 广播交换器
     */
    @Bean
    Binding bindingExchange(Queue fanoutQueue,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);
    }
    /**
     * 将 广播队列 和 广播交换器 绑定
     *
     * @param fanoutQueue2    广播队列
     * @param fanoutExchange 广播交换器
     */
    @Bean
    Binding bindingExchange2(Queue fanoutQueue2,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }
    //------------------------------------------------end-----------------------------------------------------------------------------------

    //----------------------------主题交换器(Topic Exchange) -----------------------------------------------------------------------------------
    /**
     * 主题模式队列
     * <li>路由格式必须以 . 分隔，比如 user.email 或者 user.aaa.email</li>
     * <li>通配符 * ，代表一个占位符，或者说一个单词，比如路由为 user.*，那么 user.email 可以匹配，但是 user.aaa.email 就匹配不了</li>
     * <li>通配符 # ，代表一个或多个占位符，或者说一个或多个单词，比如路由为 user.#，那么 user.email 可以匹配，user.aaa.email 也可以匹配</li>
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitMqConstant.TOPIC_MODE_EX);
    }
    /**
     * 主题模式绑定分列模式
     *
     * @param fanoutExchange 分列模式交换器
     * @param topicExchange  主题模式交换器
     */
    @Bean
    public Binding topicBinding1(FanoutExchange fanoutExchange, TopicExchange topicExchange) {
        return BindingBuilder.bind(fanoutExchange).to(topicExchange).with(RabbitMqConstant.TOPIC_ROUTING_KEY_ONE);
    }

    /**
     * 主题模式绑定队列
     *
     * @param queue      队列
     * @param topicExchange 主题模式交换器
     */
    @Bean
    public Binding topicBinding2(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(RabbitMqConstant.TOPIC_ROUTING_KEY_TWO);
    }
    /**
     * 主题模式绑定队列
     *
     * @param fanoutQueue    队列
     * @param topicExchange 主题模式交换器
     */
    @Bean
    public Binding topicBinding3(Queue fanoutQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(fanoutQueue).to(topicExchange).with(RabbitMqConstant.TOPIC_ROUTING_KEY_THREE);
    }
    //------------------------------------------------end-----------------------------------------------------------------------------------
}
