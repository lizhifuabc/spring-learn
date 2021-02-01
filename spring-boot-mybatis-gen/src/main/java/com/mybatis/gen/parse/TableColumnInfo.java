package com.mybatis.gen.parse;

import lombok.Builder;
import lombok.Data;

/**
 * TableColumnInfo
 *
 * @author lizhifu
 * @date 2021/2/1
 */
@Data
@Builder
public class TableColumnInfo {
    /**
     * 字段名称
     */
    private String columnName;
    /**
     * 字段类型
     */
    private String columnType;
    /**
     * 注释
     */
    private String columnComment;
    /**
     * 是否主键
     */
    private boolean pk;
    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * 字段class
     */
    private String fieldClass;

}
