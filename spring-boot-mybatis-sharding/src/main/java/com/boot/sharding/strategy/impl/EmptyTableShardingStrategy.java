package com.boot.sharding.strategy.impl;

import com.boot.sharding.strategy.TableShardingStrategy;

/**
 * 不使用策略
 *
 * @author lizhifu
 * @date 2020/12/30
 */
public class EmptyTableShardingStrategy implements TableShardingStrategy {
    @Override
    public String getTableName(String logicTable, Object o) {
        throw new UnsupportedOperationException("EmptyTableShardingStrategy 无可用策略");
    }
}
