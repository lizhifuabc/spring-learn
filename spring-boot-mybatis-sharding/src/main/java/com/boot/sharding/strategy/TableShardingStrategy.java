package com.boot.sharding.strategy;
/**
 * 分表策略
 *
 * @author lizhifu
 * @date 2020/12/30
 */
public interface TableShardingStrategy<T>{
    /**
     * 获取真实表名称
     * @param logicTable
     * @param t 分表参数
     * @return
     */
    String getTableName(String logicTable, T t);
}
