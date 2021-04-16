package com.mybatis.provider.core.provider;

import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;

/**
 * SelectByPrimaryKeyInSqlProvider
 *
 * @author lizhifu
 * @date 2021/1/6
 */
public class SelectByPrimaryKeyInSqlProvider extends SqlProviderSupport {
    @Override
    public String sql(Object entity, ProviderContext context) {
        return new SQL()
                .SELECT(table.getSelectColumns())
                .FROM(table.getTableName())
                .WHERE(table.getPrimaryKeyWhere())
                .toString();
    }
}
