package com.boot.gen.mapper;


import com.boot.gen.domain.GenTable;
import org.apache.ibatis.annotations.Mapper;

/**
 * 业务 数据层
 * 
 * @author ruoyi
 */
@Mapper
public interface GenTableMapper
{
    /**
     * 查询据库列表
     * 
     * @param tableName 表名称
     * @return 数据库表
     */
    GenTable selectTableByName(String tableName);
}
