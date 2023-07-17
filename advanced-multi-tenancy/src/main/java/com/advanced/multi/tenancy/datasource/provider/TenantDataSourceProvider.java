package com.advanced.multi.tenancy.datasource.provider;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * 多数据源提供者
 *
 * @author lizhifu
 * @since 2023/7/16
 */
public interface TenantDataSourceProvider {
    /**
     * 设置 DefaultDataSourceCreator
     *
     * @param defaultDataSourceCreator defaultDataSourceCreator
     */
    void setDefaultDataSourceCreator(DefaultDataSourceCreator defaultDataSourceCreator);

    /**
     * 加载所有数据源
     *
     * @return 所有数据源，key为数据源名称
     */
    Map<String, DataSource> loadDataSources();


    /**
     * 加载共享数据源为key的租户列表
     *
     * @param ds 共享数据源key
     * @return 租户列表
     */
    List<String> loadTenants(String ds);
}
