package com.mq.common.constant;

/**
 * RabbitMq 常量
 *
 * @author lizhifu
 * @date 2020/12/17
 */
public class RabbitMqConstant {
    /**
     * 普通队列--带死信队列模式
     */
    public final static String BUSINESS_QUERY = "business_query";
    /**
     * 普通队列--接收死信队列
     */
    public final static String BUSINESS_QUERY_DEAD = "business_query_dead";
    /**
     * 普通exchange
     */
    public final static String BUSINESS_TOPIC_EX = "business_topic_exchange";
    /**
     * 死信队列exchange
     */
    public final static String DLX_EXCHANGE = "dlx_exchange";
    /**
     * 直接模式
     */
    public final static String DIRECT_QUEUE = "queue.direct.1";
    /**
     * fanout模式
     */
    public final static String FANOUT_QUEUE = "queue.fanout.1";
    /**
     * fanout模式
     */
    public final static String FANOUT_MODE_EX = "exchange.fanout.1";
    /**
     * 主题模式
     */
    public static final String TOPIC_MODE_EX = "query.topic.1";
    /**
     * 路由1
     */
    public static final String TOPIC_ROUTING_KEY_ONE = "queue.#";
    /**
     * 路由2
     */
    public static final String TOPIC_ROUTING_KEY_TWO = "*.queue";
    /**
     * 路由3
     */
    public static final String TOPIC_ROUTING_KEY_THREE = "3.queue";
    /**
     * 延迟队列
     */
    public static final String DELAY_QUEUE = "delay.queue";

    /**
     * 延迟队列交换器
     */
    public static final  String DELAY_MODE_EX = "delay_exchange";
}
