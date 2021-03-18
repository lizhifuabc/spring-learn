package com.mybatis.gen.domain;

import lombok.Data;

/**
 * GenTableUnique
 *
 * @author lizhifu
 * @date 2021/3/17
 */
@Data
public class GenTableColumnUnique {
    /**
     * 列名称
     * */
    private String columnName;
    /** JAVA字段名 */
    private String javaField;
}
