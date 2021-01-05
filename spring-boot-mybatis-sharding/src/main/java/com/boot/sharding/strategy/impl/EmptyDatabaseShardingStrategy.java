package com.boot.sharding.strategy.impl;

import com.boot.sharding.strategy.DatabaseShardingStrategy;

/**
 * 不使用策略
 *
 * @author lizhifu
 * @date 2020/12/30
 */
public class EmptyDatabaseShardingStrategy implements DatabaseShardingStrategy {
    @Override
    public String getSchemaName(String logicTable, Object o) {
        throw new UnsupportedOperationException("EmptyDatabaseShardingStrategy 无可用策略");
    }
}
