package com.boot.sharding.strategy.impl;

import com.boot.sharding.strategy.DatabaseShardingStrategy;

/**
 * hash策略
 *
 * @author lizhifu
 * @date 2020/12/30
 */
public class LongHashDatabaseShardingStrategy implements DatabaseShardingStrategy<Long> {
    @Override
    public String getSchemaName(String logicTable, Long shardingKeyValue) {
        String table = logicTable + "_" + shardingKeyValue % 2;
        return table;
    }
}
