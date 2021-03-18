package com.mybatis.gen.mapper;

import com.mybatis.gen.domain.GenTable;
import com.mybatis.gen.domain.GenTableColumn;
import com.mybatis.gen.domain.GenTableUnique;

import java.util.List;

/**
 * 代码生成
 *
 * @author lizhifu
 * @date 2021/2/8
 */
public interface GenTableMapper {
    /**
     * 查询列信息
     *
     * @param tableName 表名称
     * @return 列信息
     */
    public List<GenTableColumn> selectDbTableColumnsByName(String tableName);

    /**
     * 查询表信息
     * @param tableName 表名称
     * @return 表信息
     */
    public GenTable selectDbTableByName(String tableName);
    /**
     * 查询约束
     * @param tableName
     * @return
     */
    public List<GenTableUnique> selectUniqueTableByName(String tableName);
}
