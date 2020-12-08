package com.boot.gen.service;

import com.boot.gen.domain.GenTable;
import com.boot.gen.domain.GenTableColumn;
import org.apache.velocity.VelocityContext;

import java.util.List;

/**
 * 代码生成
 *
 * @author lizhifu
 * @date 2020/12/8
 */
public interface GenService {
    /**
     * 初始化数据
     * @param tableName 表名称
     * @return 表信息
     */
    GenTable initGenTable(String tableName);

    /**
     * 初始化表字段信息
     * @param genTable 表属性
     * @return
     */
    GenTable initGenTableColumn(GenTable genTable);
    /**
     * 设置模板变量信息
     * @return 模板列表
     */
    VelocityContext prepareContext(GenTable genTable);
}
