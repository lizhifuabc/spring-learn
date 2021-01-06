package com.mybatis.provider.core.provider;

import com.mybatis.provider.core.SqlProviderSupport;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;

import java.util.stream.Stream;

/**
 * InsertSqlProvider
 *
 * @author lizhifu
 * @date 2021/1/6
 */
public class InsertSqlProvider extends SqlProviderSupport {
    @Override
    public String sql(Object criteria, ProviderContext context) {
        return new SQL()
                .INSERT_INTO(table.getTableName())
                .INTO_COLUMNS(table.getColumns())
                .INTO_VALUES(Stream.of(table.getFields()).map(this::bindParameter).toArray(String[]::new))
                .toString();
    }
}
