package com.spring.boot.multitenant.hibernate;

import com.spring.boot.multitenant.constants.DefaultConstants;
import com.spring.boot.multitenant.context.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 自定义MultiTenantConnectionProvider接口的实现,用于Hibernate多数据源场景
 *
 * @author lizhifu
 * @since 2023/6/12
 */
@Component
@Slf4j
public class CustomMultiTenantConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl implements HibernatePropertiesCustomizer {
    /**
     * 默认数据源
     */
    private final DataSource defaultDataSource;
    public CustomMultiTenantConnectionProvider(DataSource dataSource) {
        this.defaultDataSource = dataSource;
        TenantContextHolder.setDataSource(DefaultConstants.DEFAULT_TENANT_ID, dataSource);
    }
    /**
     * 在没有指定 tenantId 的情况下选择的数据源（例如启动处理）
     * @return DataSource 默认数据源
     */
    @Override
    protected DataSource selectAnyDataSource() {
        log.info("当前租户未选择数据源,使用默认数据源");
        return defaultDataSource;
    }

    /**
     * 根据 tenantId 选择数据源
     * @param tenantIdentifier 租户ID
     * @return DataSource 数据源
     */
    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        log.info("当前租户选择数据源:{}", tenantIdentifier);
        DataSource currentDataSource = TenantContextHolder.getDataSource(tenantIdentifier);
        if (currentDataSource == null) {
            log.warn("未找到租户:{}对应的数据源,使用默认数据源", tenantIdentifier);
            currentDataSource = defaultDataSource;
        }
        return currentDataSource;
    }
    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        // 设置MultiTenantConnectionProvider
        hibernateProperties.put(AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER, this);
    }
}
