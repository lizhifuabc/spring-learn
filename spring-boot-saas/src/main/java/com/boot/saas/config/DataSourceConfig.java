package com.boot.saas.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.boot.saas.datasource.DatasourceHolder;
import com.boot.saas.datasource.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @author lizhifu
 * @date 2020/12/22
 */
@Configuration
public class DataSourceConfig {
    /**
     * 默认数据源
     * @return DataSource
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
     * 保证动态数据源的前置增强先于事务
     * @return DynamicDataSource
     */
    @Bean
    @Primary
    @DependsOn({"defaultDataSource"})
    public DynamicDataSource dynamicDataSource(){
        return new DynamicDataSource();
    }
}
