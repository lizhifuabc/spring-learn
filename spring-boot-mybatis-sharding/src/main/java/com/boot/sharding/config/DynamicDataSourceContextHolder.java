package com.boot.sharding.config;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据源切换处理
 *
 * @author lizhifu
 * @date 2021/1/4
 */
@Slf4j
public class DynamicDataSourceContextHolder {
    /**
     * 使用ThreadLocal维护变量，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     *  所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = ThreadLocal.withInitial(() -> DatasourceHolder.DEFAULT_DATASOURCE);
    /**
     * 设置默认数据源
     */
    public static void setDefaultDatasource() {
        CONTEXT_HOLDER.remove();
        setCurrentDatasource(DatasourceHolder.DEFAULT_DATASOURCE);
    }
    /**
     * 设置当前数据源配置
     */
    public static void setCurrentDatasource(String datasource)
    {
        log.info("切换到{}数据源", datasource);
        CONTEXT_HOLDER.set(datasource);
    }
    /**
     * 获取当前数据源配置
     */
    public static String getCurrentDatasource()
    {
        return CONTEXT_HOLDER.get();
    }
    /**
     * 清空数据源变量
     */
    public static void clearDataSource()
    {
        CONTEXT_HOLDER.remove();
    }
}
