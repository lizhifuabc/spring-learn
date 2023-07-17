package com.advanced.multi.tenancy.datasource.creator;

import com.advanced.multi.tenancy.properties.DataSourceDetailProperties;
import com.advanced.multi.tenancy.properties.TenantProperties;

import javax.sql.DataSource;

/**
 * 数据源创建
 *
 * @author lizhifu
 * @since 2023/7/16
 */
public abstract class AbstractDataSourceCreator implements DataSourceCreator {
    protected TenantProperties properties;
    /**
     * 子类去实际创建连接池
     *
     * @param dataSourceDetailProperties 数据源信息
     * @return 实际连接池
     */
    public abstract DataSource doCreateDataSource(DataSourceDetailProperties dataSourceDetailProperties);
    @Override
    public DataSource createDataSource(DataSourceDetailProperties dataSourceDetailProperties) {
        return doCreateDataSource(dataSourceDetailProperties);
    }
}
