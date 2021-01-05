package com.boot.sharding.strategy;

/**
 * 分库策略
 *
 * @author lizhifu
 * @date 2021/1/4
 */
public interface DatabaseShardingStrategy<T> {
    /**
     * 获取实际库名
     *
     * @param originSchema 原始库名(逻辑库名)
     * @param partitionKey 分库key
     * @return
     */
    String getSchemaName(String originSchema, T partitionKey);
}