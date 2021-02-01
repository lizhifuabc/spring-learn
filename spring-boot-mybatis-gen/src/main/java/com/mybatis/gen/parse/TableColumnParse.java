package com.mybatis.gen.parse;

import com.alibaba.druid.sql.ast.statement.SQLColumnDefinition;
import com.mybatis.gen.util.CamelCaseUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字段解析
 *
 * @author lizhifu
 * @date 2021/1/29
 */
public class TableColumnParse {
    private Map<String,String> transMap = new HashMap<>();
    private TableInfo tableInfo;
    /**
     * 建表sql
     */
    private List<SQLColumnDefinition> sqlColumnDefinitionList;

    private TableColumnParse(){
        transMap.put("int",Long.class.getSimpleName());
        transMap.put("bigint",Long.class.getSimpleName());
        transMap.put("varchar",String.class.getSimpleName());
        transMap.put("text",String.class.getSimpleName());
        transMap.put("char",String.class.getSimpleName());
        transMap.put("clob",String.class.getSimpleName());
        transMap.put("blob",String.class.getSimpleName());
        transMap.put("decimal", BigDecimal.class.getSimpleName());
        transMap.put("json",String.class.getSimpleName());
        transMap.put("time", LocalDateTime.class.getSimpleName());
        transMap.put("date",LocalDateTime.class.getSimpleName());
        transMap.put("datetime",LocalDateTime.class.getSimpleName());
        transMap.put("timestamp",LocalDateTime.class.getSimpleName());
    }
    /**
     * 创建
     * @return
     */
    public static TableColumnParse create(){
        return new TableColumnParse();
    }
    public TableColumnParse withTable(List<SQLColumnDefinition> sqlColumnDefinitionList){
        this.sqlColumnDefinitionList = sqlColumnDefinitionList;
        return this;
    }
    public TableColumnParse withTableInfo(TableInfo tableInfo){
        this.tableInfo = tableInfo;
        return this;
    }
    public List<TableColumnInfo> parse(){
        List<TableColumnInfo> list = new ArrayList<>();
        sqlColumnDefinitionList.stream().forEach(sqlColumnDefinition->{
            if(sqlColumnDefinition.isPrimaryKey() && tableInfo != null){
                tableInfo.setKeyProperty(CamelCaseUtil.underlineToCamelCase(sqlColumnDefinition.getColumnName()));
                tableInfo.setKeyColumn(sqlColumnDefinition.getColumnName());
            }
            TableColumnInfo tableColumnInfo = TableColumnInfo.builder()
                    .pk(sqlColumnDefinition.isPrimaryKey())
                    .columnType(sqlColumnDefinition.getDataType().getName())
                    .columnName(sqlColumnDefinition.getColumnName())
                    .fieldName(CamelCaseUtil.underlineToCamelCase(sqlColumnDefinition.getColumnName()))
                    .columnComment(sqlColumnDefinition.getComment().toString())
                    .fieldClass(transMap.get(sqlColumnDefinition.getDataType().getName()))
                    .build();
            list.add(tableColumnInfo);
        });
        return list;
    }
}
