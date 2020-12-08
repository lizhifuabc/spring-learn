package com.boot.gen.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 表信息 gen_table
 * 
 * @author lizhifu
 */
@Data
public class GenTable
{
    /** 表名称 */
    private String tableName;
    /** 表描述 */
    private String tableComment;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 实体类名称(首字母大写) */
    private String className;
    /** 主键信息 */
    private GenTableColumn pkColumn;
    /** 表列信息 */
    private List<GenTableColumn> columns;
}