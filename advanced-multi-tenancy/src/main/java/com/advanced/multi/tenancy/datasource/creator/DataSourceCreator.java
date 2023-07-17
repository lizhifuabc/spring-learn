package com.advanced.multi.tenancy.datasource.creator;

import com.advanced.multi.tenancy.properties.DataSourceDetailProperties;

import javax.sql.DataSource;

/**
 * 数据源创建
 *
 * @author lizhifu
 * @since 2023/7/16
 */
public interface DataSourceCreator {
    /**
     * 通过属性创建数据源
     *
     * @param dataSourceDetailProperties 数据源属性
     * @return 被创建的数据源
     */
    DataSource createDataSource(DataSourceDetailProperties dataSourceDetailProperties);
}
