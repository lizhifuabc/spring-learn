package com.boot.sharding.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.boot.sharding.util.SpringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 扩展 Spring 的 AbstractRoutingDataSource 抽象类，重写 determineCurrentLookupKey 方法
 * 动态数据源
 * determineCurrentLookupKey() 方法决定使用哪个数据源
 * @author lizhifu
 * @date 2020/12/22
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * 返回值是DataSource的唯一名字，表示使用该名字对应的DataSource
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getCurrentDatasource();
    }
    /**
     * 决定使用哪个数据源之前需要把多个数据源的信息以及默认数据源信息配置好
     */
    public DynamicDataSource() {
        //AbstractRoutingDataSource的内部使用了一个map存放多个数据源，key是数据源的唯一名字（可以任意命名，但是要保证唯一），value是对应的DataSource
        super.setTargetDataSources(DatasourceHolder.DATASOURCE_MAP);
        super.afterPropertiesSet();
    }
    public static void setDataSource(String dataSource,DruidDataSource druidDataSource) {
        DatasourceHolder.DATASOURCE_MAP.put(dataSource, druidDataSource);
        DynamicDataSourceContextHolder.setCurrentDatasource(dataSource);
        DynamicDataSource dynamicDataSource = (DynamicDataSource) SpringUtils.getBean("dynamicDataSource");
        //重新加载数据源targetDataSources
        dynamicDataSource.afterPropertiesSet();
    }
}
