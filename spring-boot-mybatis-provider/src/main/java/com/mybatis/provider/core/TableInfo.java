package com.mybatis.provider.core;

import com.mybatis.provider.core.annotation.NoColumn;
import com.mybatis.provider.core.annotation.PrimaryKey;
import com.mybatis.provider.core.annotation.Table;
import com.mybatis.provider.core.util.CamelUtil;
import com.mybatis.provider.core.util.ReflectionUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.stream.Stream;

/**
 * 表相关信息
 *
 * @author lizhifu
 * @date 2021/1/6
 */
@Data
public class TableInfo {
    /**
     * 主键数据返回字段 {@link BaseEntity} keyProperty
     * 即数据插入时将ID赋值给keyProperty
     */
    public static final String DEFAULT_PRIMARY_KEY = "keyProperty";
    /**
     * 表名
     */
    private String tableName;
    /**
     * 实体类型不含@NoColunm注解的field
     */
    private Field[] fields;
    /**
     * 主键column(驼峰转为下划线)
     */
    private String primaryKeyColumn;
    /**
     * 主键column(驼峰)
     */
    private String primaryKey;
    /**
     * 所有列名
     */
    private String[] columns;
    /**
     * 所有select sql的列名，有带下划线的将其转为aa_bb AS aaBb
     */
    private String[] selectColumns;

    private TableInfo() {}
    /**
     * 获取TableInfo
     *
     * @param mapperType mapper类型
     * @return {@link TableInfo}
     */
    public static TableInfo of(Class<?> mapperType) {
        // 获取实体类
        Class<?> entityClass = entityType(mapperType);
        // 获取不含有@NoColumn注解的fields
        Field[] fields = excludeNoColumnField(ReflectionUtils.getFields(entityClass, null));
        TableInfo tableInfo = new TableInfo();
        tableInfo.fields = fields;
        tableInfo.tableName = tableName(entityClass);
        tableInfo.primaryKeyColumn =  primaryKeyColumn(fields);
        tableInfo.primaryKey =  primaryKey(fields);
        tableInfo.columns = columns(fields);
        tableInfo.selectColumns = selectColumns(fields);
        return tableInfo;
    }
    /**
     * 获取BaseMapper接口中的泛型类型
     * @param mapperType mapper类型
     * @return clz
     */
    private static Class<?> entityType(Class<?> mapperType) {
        //获取泛型接口
        return Stream.of(mapperType.getGenericInterfaces())
                //获取实现的第一个接口类型
                .filter(ParameterizedType.class::isInstance)
                .map(ParameterizedType.class::cast)
                .filter(type -> type.getRawType() == BaseMapper.class)
                .findFirst()
                //获取到实际的泛型中参数类型
                .map(type -> type.getActualTypeArguments()[0])
                .filter(Class.class::isInstance)
                .map(Class.class::cast)
                .orElseThrow(() -> new IllegalStateException("未找到BaseMapper的泛型类 " + mapperType.getName() + "."));
    }
    /**
     * 获取表名称
     *
     * @param entityType  实体类型
     * @return      表名
     */
    private static String tableName(Class<?> entityType) {
        Table table = entityType.getAnnotation(Table.class);
        if(null == table){
            throw new IllegalStateException("未找到Table注解:"+entityType);
        }
        return table.value();
    }
    /**
     * 过滤含有@Transient注解的field
     * @param totalField  entityClass所有的字段
     * @return   不包含@Transient注解的fields
     */
    private static Field[] excludeNoColumnField(Field[] totalField) {
        return Stream.of(totalField)
                //过滤含有@NoColumn注解的field
                .filter(field -> !field.isAnnotationPresent(NoColumn.class))
                .toArray(Field[]::new);
    }
    /**
     * 如果fields中含有@Primary的字段，则返回该字段名为主键，否则默认'id'为主键名
     *
     * @param fields entityClass所有fields
     * @return 主键column(驼峰转为下划线)
     */
    private static String primaryKeyColumn(Field[] fields) {
        return Stream.of(fields).filter(field -> field.isAnnotationPresent(PrimaryKey.class))
                .findFirst()    //返回第一个primaryKey的field
                .map(TableInfo::columnName)
                .orElse(DEFAULT_PRIMARY_KEY);
    }
    /**
     * 如果fields中含有@Primary的字段，则返回该字段名为主键，否则默认'id'为主键名
     *
     * @param fields entityClass所有fields
     * @return 主键column(驼峰)
     */
    private static String primaryKey(Field[] fields) {
        return Stream.of(fields).filter(field -> field.isAnnotationPresent(PrimaryKey.class))
                .findFirst()    //返回第一个primaryKey的field
                .map(field -> field.getName())
                .orElse(DEFAULT_PRIMARY_KEY);
    }
    /**
     * 获取单个属性对应的数据库字段
     *
     * @param field entityClass中的field
     * @return  字段对应的column
     */
    public static String columnName(Field field) {
        return "`" + CamelUtil.camel2Underscore(field.getName()) + "`";
    }
    /**
     * 获取所有pojo所有属性对应的数据库字段 (不包含pojo中含有@NoColumn主键的属性)
     *
     * @param fields entityClass所有fields
     * @return        所有的column名称
     */
    private static String[] columns(Field[] fields) {
        return Stream.of(fields).map(TableInfo::columnName).toArray(String[]::new);
    }
    /**
     * 获取查询对应的字段 (不包含pojo中含有@NoColumn主键的属性)
     *
     * @param fields p
     * @return  所有需要查询的查询字段
     */
    private static String[] selectColumns(Field[] fields) {
        return Stream.of(fields).map(TableInfo::selectColumnName).toArray(String[]::new);
    }
    /**
     * 获取单个属性对应的数据库字段（带有下划线字段将其转换为"字段 AS pojo属性名"形式）
     *
     * @param field  字段
     * @return      带有下划线字段将其转换为"字段 AS pojo属性名"形式
     */
    private static String selectColumnName(Field field) {
        String camel = columnName(field);
        return camel.contains("_") ? camel + " AS `" + field.getName() + "`" : camel;
    }
    /**
     * 获取主键的where条件，如 id = #{id}
     *
     * @return  主键where条件
     */
    public String getPrimaryKeyWhere() {
        return this.primaryKeyColumn + " = #{" + this.primaryKey + "}";
    }
    /**
     * 获取该字段的参数赋值语句，如 user_name = #{userName}
     * @param field  字段
     * @return       参数赋值语句
     */
    public static String assignParameter(Field field) {
        return columnName(field) + " = " + String.format("#{%s}", field.getName());
    }
}
