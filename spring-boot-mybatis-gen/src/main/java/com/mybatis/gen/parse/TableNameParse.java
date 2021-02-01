package com.mybatis.gen.parse;

import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateTableStatement;
import com.mybatis.gen.util.CamelCaseUtil;

/**
 * 表名称解析
 *
 * @author lizhifu
 * @date 2021/1/29
 */
public class TableNameParse {
    /**
     * MySqlCreateTableStatement
     */
    private MySqlCreateTableStatement mySqlCreateTableStatement;

    /**
     * classType
     */
    private String classType;
    private TableNameParse(){}
    /**
     * 创建
     * @return
     */
    public static TableNameParse create(){
        return new TableNameParse();
    }
    public TableNameParse withTable(MySqlCreateTableStatement mySqlCreateTableStatement){
        this.mySqlCreateTableStatement = mySqlCreateTableStatement;
        return this;
    }
    public TableNameParse withClassType(String classType){
        this.classType = classType;
        return this;
    }
    public TableInfo parse(){
        TableInfo tableInfo = TableInfo.builder()
                .tableName(mySqlCreateTableStatement.getName().getSimpleName())
                .tableComment(mySqlCreateTableStatement.getComment().toString())
                .className(CamelCaseUtil.upperCaseFirst(CamelCaseUtil.underlineToCamelCase(mySqlCreateTableStatement.getName().getSimpleName())+classType))
                .build();
        return tableInfo;
    }
}
