package com.mybatis.provider.core.provider;

import com.mybatis.provider.core.SqlProviderSupport;
import com.mybatis.provider.core.TableInfo;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;

import java.util.stream.Stream;

/**
 * UpdateSqlProvider
 *
 * @author lizhifu
 * @date 2021/1/6
 */
public class UpdateSqlProvider extends SqlProviderSupport {
    @Override
    public String sql(Object entity, ProviderContext context) {
        return new SQL()
                .UPDATE(table.getTableName())
                .SET(Stream.of(table.getFields())
                        .filter(field -> !table.getPrimaryKeyColumn().equals(TableInfo.columnName(field)))
                        .map(TableInfo::assignParameter).toArray(String[]::new))
                .WHERE(table.getPrimaryKeyWhere())
                .toString();
    }
}
