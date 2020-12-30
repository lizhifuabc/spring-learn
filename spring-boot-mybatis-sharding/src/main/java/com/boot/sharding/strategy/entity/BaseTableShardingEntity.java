package com.boot.sharding.strategy.entity;

import lombok.Data;

/**
 * 基础参数
 *
 * @author lizhifu
 * @date 2020/12/30
 */
@Data
public class BaseTableShardingEntity {
    /**
     * 表名分隔符
     */
    private String tableSeparator = "_";
}