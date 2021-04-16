package com.mybatis.provider.core.provider;

import com.mybatis.provider.core.TableInfo;
import com.mybatis.provider.core.util.ReflectionUtils;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;

import java.util.stream.Stream;

/**
 * SelectByEntitySqlProvider
 *
 * @author lizhifu
 * @date 2021/1/6
 */
public class SelectByEntitySqlProvider extends SqlProviderSupport {
    @Override
    public String sql(Object entity, ProviderContext context) {
        return new SQL()
                .SELECT(table.getSelectColumns())
                .FROM(table.getTableName())
                .WHERE(Stream.of(table.getFields())
                        .filter(field -> ReflectionUtils.getFieldValue(field, entity) != null)
                        .map(TableInfo::assignParameter)
                        .toArray(String[]::new)).ORDER_BY(table.getPrimaryKeyColumn() + " DESC").toString();
    }
}
