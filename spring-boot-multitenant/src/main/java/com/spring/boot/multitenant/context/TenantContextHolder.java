package com.spring.boot.multitenant.context;

import com.spring.boot.multitenant.constants.DefaultConstants;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 存储/获取当前线程的租户信息
 *
 * @author lizhifu
 * @since 2023/6/12
 */
public class TenantContextHolder {
    /**
     * 当前线程的租户信息,TODO 使用阿里的TransmittableThreadLocal
     */
    private static final ThreadLocal<String> CURRENT_CONTEXT = new ThreadLocal<>();
    /**
     * 多数据源合集
     */
    private static final ConcurrentHashMap<String, DataSource> DATASOURCE_MAP = new ConcurrentHashMap<>();

    /**
     * 设置多数据源
     * @param tenantId 租户ID
     * @param dataSource 数据源
     */
    public static void setDataSource(String tenantId, DataSource dataSource) {
        DATASOURCE_MAP.putIfAbsent(tenantId, dataSource);
    }

    /**
     * 获取多数据源
     * @param tenantId 租户ID
     * @return DataSource 数据源
     */
    public static DataSource getDataSource(String tenantId) {
        return DATASOURCE_MAP.get(tenantId);
    }
    /**
     * 获取租户ID
     * @return 租户ID
     */
    public static String getTenantId() {
        return Optional.ofNullable(CURRENT_CONTEXT.get()).orElse(DefaultConstants.DEFAULT_TENANT_ID);
    }

    /**
     * 设置租户ID
     * @param tenantId 租户ID
     */
    public static void setTenantId(final String tenantId) {
        CURRENT_CONTEXT.set(Optional.ofNullable(tenantId).orElse(DefaultConstants.DEFAULT_TENANT_ID));
    }
    /**
     * 清除租户ID
     */
    public static void clear() {
        CURRENT_CONTEXT.remove();
    }
}
