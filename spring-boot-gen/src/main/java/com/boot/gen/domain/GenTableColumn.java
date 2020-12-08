package com.boot.gen.domain;


import lombok.Data;

/**
 * 列信息 gen_table_column
 * 
 * @author ruoyi
 */
@Data
public class GenTableColumn
{
    /** 列名称 */
    private String columnName;
    /**数据类型*/
    private String dataType;
    /** 列描述 */
    private String columnComment;
    /** 列类型 */
    private String columnType;
    /** 是否主键（1是） */
    private boolean pk;
    /** 是否自增（1是） */
    private boolean increment;
    /** 是否必填（1是） */
    private boolean required;
    /** 排序 */
    private Integer sort;


    /** JAVA类型 */
    private String javaType;
    /** JAVA字段名 */
    private String javaField;
}
