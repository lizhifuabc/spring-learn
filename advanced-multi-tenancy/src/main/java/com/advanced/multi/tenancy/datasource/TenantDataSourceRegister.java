package com.advanced.multi.tenancy.datasource;

import com.advanced.multi.tenancy.properties.DataSourceDetailProperties;

/**
 * 多租户数据源注册
 *
 * @author lizhifu
 * @since 2023/7/16
 */
public interface TenantDataSourceRegister {
    /**
     * 注册
     * @param dataSourceDetailProperties 多租户数据源配置
     * @return 是否注册成功
     */
    boolean register(DataSourceDetailProperties dataSourceDetailProperties);

    /**
     * 移除
     * @param tenant 租户
     * @return 是否移除成功
     */
    boolean remove(String tenant);
}
