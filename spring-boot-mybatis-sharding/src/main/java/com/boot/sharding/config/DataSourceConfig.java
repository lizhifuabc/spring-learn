package com.boot.sharding.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @author lizhifu
 * @date 2021/1/4
 */
@Configuration
public class DataSourceConfig {
    /**
     * 默认数据源
     * @return
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource defaultDataSource() {
        DataSource dataSource = DruidDataSourceBuilder.create().build();
        DatasourceHolder.DATASOURCE_MAP.put(DatasourceHolder.DEFAULT_DATASOURCE,dataSource);
        return dataSource;
    }
    /**
     * 动态数据源 DependsOn 在defaultDataSource之后才能初始化
     * @return
     */
    @Bean
    @Primary
    @DependsOn({"defaultDataSource","ds_0","ds_1"})
    public DynamicDataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        return dynamicDataSource;
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.ds0")
    public DataSource ds_0(){
        DataSource dataSource = DruidDataSourceBuilder.create().build();
        DatasourceHolder.DATASOURCE_MAP.put("ds_0",dataSource);
        return dataSource;
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.ds1")
    public DataSource ds_1(){
        DataSource dataSource = DruidDataSourceBuilder.create().build();
        DatasourceHolder.DATASOURCE_MAP.put("ds_1",dataSource);
        return dataSource;
    }
    /**
     * 注入事务管理器
     *
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(@Autowired DynamicDataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
