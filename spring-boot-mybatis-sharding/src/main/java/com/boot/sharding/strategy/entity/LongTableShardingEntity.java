package com.boot.sharding.strategy.entity;

import lombok.Data;

/**
 * 基础参数
 *
 * @author lizhifu
 * @date 2020/12/30
 */
@Data
public class LongTableShardingEntity extends BaseTableShardingEntity{
    /**
     * 表分片数
     */
    private int tableNum = 2;
    /**
     * 分表字段值
     */
    private Long shardingKey;
}