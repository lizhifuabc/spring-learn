package com.mybatis.common.xml;

import com.mybatis.common.util.ClassUtils;
import com.mybatis.common.util.StrUtils;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 字段
 *
 * @author lizhifu
 * @date 2020/12/14
 */
public class ColumnXml {
    /**
     * 获取字段
     * @param entityClass 实体
     * @return 字段
     */
    public static List<String> getColumnList(final Class entityClass) {
        List cols = new ArrayList<String>(16);
        ReflectionUtils.doWithFields(entityClass,
                field -> cols.add(StrUtils.camelToUnderscore(field.getName())),
                ClassUtils::isNormal);
        return cols;
    }
    /**
     * 获取字段value
     * @param entity 实体
     * @return 字段
     */
    public static List<Object> getColumnListValue(final Object entity) {
        Class<?> entityClass = entity.getClass();
        List<Object> cols = new ArrayList<Object>(16);
        ReflectionUtils.doWithFields(entityClass,field ->{
                    field.setAccessible(true);
                    Object value = ReflectionUtils.getField(field, entity);
                    cols.add(value);
                },
                ClassUtils::isNormal);
        return cols;
    }
    /**
     * 获取字段
     * @param entityClass 实体
     * @return 字段
     */
    public static String getColumns(final Class entityClass) {
        return StrUtils.join(ColumnXml.getColumnList(entityClass), ", ");
    }
}
