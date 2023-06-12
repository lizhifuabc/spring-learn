package com.spring.boot.multitenant.datasource.base;

import com.spring.boot.multitenant.common.context.TenantContextHolder;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

/**
 * 多租户数据源工厂
 *
 * @author lizhifu
 * @since 2023/6/12
 */
@Component
@Slf4j
public class MultiTenantDataSourceFactory implements InitializingBean {
    private final SysTenantRepository sysTenantRepository;
    private final DataSource dataSource;
    public MultiTenantDataSourceFactory(SysTenantRepository sysTenantRepository, DataSource dataSource) {
        this.sysTenantRepository = sysTenantRepository;
        this.dataSource = dataSource;
    }
    private DataSource createDataSource(DataSource defaultDataSource, SysTenant sysTenant) {
        HikariDataSource defaultHikariDataSource = (HikariDataSource) defaultDataSource;
        Properties defaultDataSourceProperties = defaultHikariDataSource.getDataSourceProperties();
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(sysTenant.getDriverClassName());
        hikariConfig.setJdbcUrl(sysTenant.getUrl());
        hikariConfig.setUsername(sysTenant.getUsername());
        hikariConfig.setPassword(sysTenant.getPassword());
        // 使用默认数据源的属性配置
        defaultDataSourceProperties.forEach((key, value) -> hikariConfig.addDataSourceProperty(String.valueOf(key), value));
        return new HikariDataSource(hikariConfig);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("初始化多租户数据源");
        List<SysTenant> all = sysTenantRepository.findAll();
        all.forEach(sysTenant -> {
            log.info("初始化多租户数据源:{}",sysTenant);
            TenantContextHolder.setDataSource(sysTenant.getTenantId(),createDataSource(dataSource, sysTenant));
        });
    }
}
