package com.mybatis.gen.service;

import com.mybatis.gen.domain.GenInfo;
import com.mybatis.gen.domain.GenTable;
import com.mybatis.gen.domain.GenTableColumn;
import com.mybatis.gen.domain.GenTableUnique;

import java.util.List;

/**
 * 代码生成
 *
 * @author lizhifu
 * @date 2021/3/18
 */
public interface GenService {
    /**
     * 初始化表信息
     * @param genInfo
     * @return
     */
    GenTable genTable(GenInfo genInfo);
    /**
     * 查询字段：包含单字段约束+主键等等
     * @param genInfo
     * @param genTable
     * @return
     */
    List<GenTableColumn> genTableColumns(GenInfo genInfo, GenTable genTable);

    /**
     * 查询多列表约束
     * @param genInfo
     * @return
     */
    List<GenTableUnique> genTableUniques(GenInfo genInfo);
}
