package com.boot.sharding.strategy.impl;

import com.boot.sharding.strategy.TableShardingStrategy;

/**
 * hash策略
 *
 * @author lizhifu
 * @date 2020/12/30
 */
public class LongHashTableShardingStrategy implements TableShardingStrategy<Long> {
    @Override
    public String getTableName(String logicTable, Long shardingKeyValue) {
        String table = logicTable + "_" + shardingKeyValue % 2;
        return table;
    }
}
