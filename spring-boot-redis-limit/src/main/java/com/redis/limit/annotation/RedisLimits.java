package com.redis.limit.annotation;

import java.lang.annotation.*;

/**
 * 多维度注解
 *
 * @author lizhifu
 * @date 2020/12/23
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLimits {
    RedisLimit[] value();
}
