package com.boot.gen.mapper;

import com.boot.gen.domain.GenTableColumn;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 字段
 * 
 * @author lizhifu
 */
@Repository
public interface GenTableColumnMapper
{
    /**
     * 根据表名称查询列信息
     * 
     * @param tableName 表名称
     * @return 列信息
     */
    List<GenTableColumn> selectDbTableColumnsByName(String tableName);
}
