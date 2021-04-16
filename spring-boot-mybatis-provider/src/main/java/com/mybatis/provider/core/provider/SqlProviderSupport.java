package com.mybatis.provider.core.provider;

import com.mybatis.provider.core.TableInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * sql provide 基础包
 *
 * @author lizhifu
 * @date 2021/1/6
 */
@Slf4j
public abstract class SqlProviderSupport {
    /**
     * 表信息
     */
    protected TableInfo table;
    /**
     * 缓存tableInfo
     * key ：Class<?> mapperType
     * value : tableInfo
     */
    private static Map<Class<?>, TableInfo> tableCache = new ConcurrentHashMap<>(128);
    /**
     * 获取SQL
     * @param entity
     * @param context
     * @return
     */
    public abstract String sql(Object entity, ProviderContext context);

    public String buildSql(Object criteria,ProviderContext context) {
        this.table = tableInfo(context);
        String sql = sql(criteria, context);
        log.info("生成的sql[{}]",sql);
        return sql;
    }
    /**
     * 缓存表信息结构
     */
    private TableInfo tableInfo(ProviderContext context) {
        return tableCache.computeIfAbsent(context.getMapperType(), TableInfo::of);
    }
    /**
     * 绑定参数
     * @param field  字段
     * @return 参数格式
     */
    public String bindParameter(Field field) {
        return String.format("#{%s}", field.getName());
    }
}
