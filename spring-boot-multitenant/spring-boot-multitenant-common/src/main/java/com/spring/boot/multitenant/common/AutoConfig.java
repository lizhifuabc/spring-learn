package com.spring.boot.multitenant.common;

import com.spring.boot.multitenant.common.config.WebConfig;
import com.spring.boot.multitenant.common.constants.DefaultConstants;
import com.spring.boot.multitenant.common.hibernate.DatabaseMultiTenantConnectionProvider;
import com.spring.boot.multitenant.common.hibernate.SchemaMultiTenantConnectionProvider;
import com.spring.boot.multitenant.common.hibernate.TenantIdResolver;
import com.spring.boot.multitenant.common.interceptor.TenantIdInterceptor;
import com.spring.boot.multitenant.common.properties.MultiTenantProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * 自动配置类
 *
 * @author lizhifu
 * @since 2023/6/12
 */
@AutoConfiguration
@EnableConfigurationProperties({MultiTenantProperties.class})
@Import({WebConfig.class})
@Slf4j
public class AutoConfig {

    @Bean
    public TenantIdInterceptor tenantIdInterceptor(){
        return new TenantIdInterceptor();
    }
    @Bean
    public TenantIdResolver tenantIdResolver(){
        return new TenantIdResolver();
    }
    @Bean
    @ConditionalOnProperty(prefix = DefaultConstants.MULTI_TENANT, name = "multiTenantType", havingValue = "DATABASE")
    public DatabaseMultiTenantConnectionProvider databaseMultiTenantConnectionProvider(DataSource dataSource){
        log.info("多租户DATABASE模式初始化:{}", DefaultConstants.MULTI_TENANT);
        return new DatabaseMultiTenantConnectionProvider(dataSource);
    }
    @Bean
    @ConditionalOnProperty(prefix = DefaultConstants.MULTI_TENANT, name = "multiTenantType", havingValue = "SCHEMA")
    public SchemaMultiTenantConnectionProvider schemaMultiTenantConnectionProvider(DataSource dataSource){
        log.info("多租户SCHEMA模式初始化:{}", DefaultConstants.MULTI_TENANT);
        return new SchemaMultiTenantConnectionProvider(dataSource);
    }
}
