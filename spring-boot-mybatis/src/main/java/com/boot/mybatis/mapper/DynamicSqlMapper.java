package com.boot.mybatis.mapper;

import com.boot.mybatis.annotation.DynamicSql;

/**
 * 动态sql示例
 *
 * @author lizhifu
 * @since 2023/7/16
 */
public interface DynamicSqlMapper {
    @DynamicSql
    Long count();
}
