package com.boot.sharding.annotation;

import com.boot.sharding.strategy.TableShardingStrategy;
import com.boot.sharding.strategy.impl.EmptyTableShardingStrategy;

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
public @interface ShardingStrategy {
    /**
     * 分表切分策略
     *
     * @return
     */
    Class<? extends TableShardingStrategy> tableShardingStrategy() default EmptyTableShardingStrategy.class;
    /**
     * 逻辑表名
     *
     * @return
     */
    String logicTable();

    /**
     * 指定分片参数
     *
     * @return
     */
    String shardingKey();
}
