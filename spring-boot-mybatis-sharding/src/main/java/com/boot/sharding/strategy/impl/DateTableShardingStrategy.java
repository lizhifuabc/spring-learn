package com.boot.sharding.strategy.impl;

import com.boot.sharding.strategy.TableShardingStrategy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 时间分表
 *
 * @author lizhifu
 * @date 2020/12/30
 */
public class DateTableShardingStrategy implements TableShardingStrategy<LocalDate> {
    @Override
    public String getTableName(String logicTable, LocalDate localDate) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
        return logicTable + "_" + localDate.format(fmt);
    }
}
