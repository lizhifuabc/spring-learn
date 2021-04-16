package com.mybatis.provider.core.provider;

import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;

/**
 * CountSqlProvider
 *
 * @author lizhifu
 * @date 2021/1/6
 */
public class CountSqlProvider extends SqlProviderSupport {
    @Override
    public String sql(Object entity, ProviderContext context) {
        return new SQL()
                .SELECT("COUNT(*)")
                .FROM(table.getTableName())
                .toString();
    }
}
