package com.mybatis.gen.domain;

import lombok.Data;

import java.util.List;

/**
 * GenTable
 *
 * @author lizhifu
 * @date 2021/2/8
 */
@Data
public class GenTable {
    /**
     * package
     */
    private String packageName;
    /**
     * 作者
     */
    private String author;
    /** 表名称 */
    private String tableName;

    /** 表描述 */
    private String tableComment;

    /** 实体类名称(首字母大写) */
    private String className;

    /** 主键信息 */
    private GenTableColumn pkColumn;

    /** 表列信息 */
    private List<GenTableColumn> columns;
}
