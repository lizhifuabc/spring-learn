package com.mybatis.gen.util.gen;


import com.mybatis.gen.domain.GenInfo;
import com.mybatis.gen.domain.GenTable;
import com.mybatis.gen.domain.GenTableColumn;
import com.mybatis.gen.util.str.StringUtils;

import java.util.Arrays;

/**
 * 代码生成
 *
 * @author lizhifu
 */
public class GenUtils {
    /** 数据库时间类型 */
    private static final String[] COLUMNTYPE_TIME = { "datetime", "time", "date", "timestamp" };
    /** 数据库数字类型 */
    private static final String[] COLUMNTYPE_NUMBER = { "tinyint", "smallint", "mediumint", "int", "number", "integer",
            "bit", "bigint", "float", "double", "decimal" };
    /**
     * 初始化表信息
     */
    public static void initTable(GenTable genTable, GenInfo genInfo)
    {
        genTable.setClassName(convertClassName(genTable.getTableName()));
        genTable.setAuthor(genInfo.getAuthor());
    }

    /**
     * 初始化列属性字段
     */
    public static void initColumnField(GenTableColumn column)
    {
        String dataType = getDbType(column.getColumnType());
        String columnName = column.getColumnName();
        // 设置java字段名
        column.setJavaField(StringUtils.toCamelCase(columnName));
        // 设置默认类型
        column.setJavaType("String");

        if (arraysContains(COLUMNTYPE_TIME, dataType))
        {
            column.setJavaType("LocalDate");
        }
        else if (arraysContains(COLUMNTYPE_NUMBER, dataType))
        {
            // 如果是浮点型 统一用BigDecimal
            String[] str = StringUtils.split(StringUtils.substringBetween(column.getColumnType(), "(", ")"), ",");
            if (str != null && str.length == 2 && Integer.parseInt(str[1]) > 0)
            {
                column.setJavaType("BigDecimal");
            }
            // 如果是整形
            else if (str != null && str.length == 1 && Integer.parseInt(str[0]) <= 10)
            {
                column.setJavaType("Integer");
            }
            // 长整形
            else
            {
                column.setJavaType("Long");
            }
        }
    }

    /**
     * 校验数组是否包含指定值
     * 
     * @param arr 数组
     * @param targetValue 值
     * @return 是否包含
     */
    public static boolean arraysContains(String[] arr, String targetValue)
    {
        return Arrays.asList(arr).contains(targetValue);
    }
    /**
     * 表名转换成Java类名
     * 
     * @param tableName 表名称
     * @return 类名
     */
    public static String convertClassName(String tableName)
    {
        boolean autoRemovePre = false;
        String tablePrefix ="";
        if (autoRemovePre && StringUtils.isNotEmpty(tablePrefix))
        {
            String[] searchList = StringUtils.split(tablePrefix, ",");
            tableName = replaceFirst(tableName, searchList);
        }
        return StringUtils.convertToCamelCase(tableName);
    }

    /**
     * 批量替换前缀
     * 
     * @param replacementm 替换值
     * @param searchList 替换列表
     * @return
     */
    public static String replaceFirst(String replacementm, String[] searchList)
    {
        String text = replacementm;
        for (String searchString : searchList)
        {
            if (replacementm.startsWith(searchString))
            {
                text = replacementm.replaceFirst(searchString, "");
                break;
            }
        }
        return text;
    }

    /**
     * 获取数据库类型字段
     * 
     * @param columnType 列类型
     * @return 截取后的列类型
     */
    public static String getDbType(String columnType)
    {
        if (StringUtils.indexOf(columnType, "(") > 0)
        {
            return StringUtils.substringBefore(columnType, "(");
        }
        else
        {
            return columnType;
        }
    }
}