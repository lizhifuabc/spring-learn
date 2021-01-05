package com.boot.sharding.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据源管理
 *
 * @author lizhifu
 * @date 2021/1/4
 */
public enum DatasourceHolder {
    /**
     * 单例
     */
    INSTANCE;
    /**
     * 默认数据源的id
     */
    public static final String DEFAULT_DATASOURCE = "defaultDataSource";

    /**
     * 管理动态数据源列表。
     */
    public static final Map<Object, Object> DATASOURCE_MAP = new ConcurrentHashMap<>();
}
