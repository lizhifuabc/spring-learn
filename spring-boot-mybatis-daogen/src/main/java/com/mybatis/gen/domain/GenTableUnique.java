package com.mybatis.gen.domain;

import lombok.Data;

import java.util.List;

/**
 * GenTableUnique
 *
 * @author lizhifu
 * @date 2021/3/17
 */
@Data
public class GenTableUnique {
    /**
     * 列名称
     * */
    private List<GenTableColumnUnique> genTableColumnUniques;
    /**
     * 约束名称
     */
    private String constraintName;
}
