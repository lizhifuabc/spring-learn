package com.mybatis.gen.domain;

import lombok.Data;

/**
 * 代码生成
 *
 * @author lizhifu
 */
@Data
public class GenTableColumn
{
    /** 列名称 */
    private String columnName;

    /** 列描述 */
    private String columnComment;

    /** 列类型 */
    private String columnType;

    /** JAVA类型 */
    private String javaType;

    /** JAVA字段名 */
    private String javaField;

    /** 是否主键（1是） */
    private String isPk;

    /** 是否自增（1是） */
    private String isIncrement;

    /** 是否必填（1是） */
    private String isRequired;
    /** 是否唯一（1是） */
    private String isUni;
    /** 排序 */
    private Integer sort;
}