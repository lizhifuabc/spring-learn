package com.boot.sharding.annotation;

import com.boot.sharding.strategy.impl.EmptyDatabaseShardingStrategy;

import java.lang.annotation.*;

/**
 * 分表注解
 *
 * @author lizhifu
 * @date 2020/12/30
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DatabaseStrategy {
    /**
     * 分库策略
     * @return
     */
    Class<? extends com.boot.sharding.strategy.DatabaseShardingStrategy> databaseShardingStrategy() default EmptyDatabaseShardingStrategy.class;
    /**
     * 指定分片参数
     *
     * @return
     */
    String shardingKey();

    /**
     * 逻辑库名
     *
     * @return
     */
    String logicSchema();
}
