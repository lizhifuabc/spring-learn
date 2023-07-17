package com.advanced.multi.tenancy.datasource.creator;

import com.advanced.multi.tenancy.properties.DataSourceDetailProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * Hikari数据源创建器
 *
 * @author lizhifu
 * @since 2023/7/16
 */
public class HikariDataSourceCreator extends AbstractDataSourceCreator {
    @Override
    public DataSource doCreateDataSource(DataSourceDetailProperties dataSourceDetailProperties) {
        HikariConfig config = dataSourceDetailProperties.getHikari();
        config.setUsername(dataSourceDetailProperties.getUsername());
        config.setPassword(dataSourceDetailProperties.getPassword());
        config.setJdbcUrl(dataSourceDetailProperties.getUrl());
        config.setPoolName(dataSourceDetailProperties.getPoolName());
        String driverClassName = dataSourceDetailProperties.getDriverClassName();
        if (StringUtils.hasLength(driverClassName)) {
            config.setDriverClassName(driverClassName);
        }
        return new HikariDataSource(config);
    }
}
