package com.mybatis.gen.parse;

import lombok.Builder;
import lombok.Data;

/**
 * 表信息
 *
 * @author lizhifu
 * @date 2021/2/1
 */
@Data
@Builder
public class TableInfo {
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 类名称：驼峰大小写
     */
    private String className;
    /**
     * 注释
     */
    private String tableComment;
    /**
     * keyColumn
     */
    private String keyColumn;
    /**
     * keyProperty
     */
    private String keyProperty;
}
