package com.redis.limit.annotation;

import com.redis.limit.enums.LimitKeyType;

import java.lang.annotation.*;

/**
 * 限流注解
 *
 * @author lizhifu
 * @date 2020/12/22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RedisLimits.class)
@Documented
public @interface RedisLimit {
    /**
     * 一定时间内最多访问次数
     */
    int max() default 10;

    /**
     * 限流key
     */
    String key();

    /**
     * 给定的时间范围 单位(毫秒)
     */
    int timeout() default 60;

    /**
     * key数据来源
     * @return
     */
    LimitKeyType keyType() default LimitKeyType.STATIC_PARAM;
}
