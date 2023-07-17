package com.advanced.multi.tenancy.datasource.proxy;

import com.advanced.multi.tenancy.exception.TenantException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.Closeable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 租户数据源代理
 * <p>
 * 1. 如果是共享数据源. 则需要切换租户的 role <br/>
 * 2. 如果是独立数据源. 则直接返回链接 <br/>
 * </p>
 * @author lizhifu
 * @since 2023/7/16
 */
@Slf4j
public class TenantDataSourceProxy extends AbstractDataSource implements Closeable {
    private static final String TENANT_NAME = "t_n";
    private final DataSource sharedDataSource;
    private final boolean shared;
    private final String dsKey;
    private final String schema;
    private final String tenant;

    public TenantDataSourceProxy(DataSource sharedDataSource, boolean shared) {
        this.sharedDataSource = sharedDataSource;
        this.shared = shared;
        this.dsKey = null;
        this.tenant = null;
        this.schema = null;
    }

    public TenantDataSourceProxy(DataSource sharedDataSource, boolean shared, String dsKey, String tenant, String schema) {
        this.sharedDataSource = sharedDataSource;
        this.shared = shared;
        this.dsKey = dsKey;
        this.tenant = tenant;
        this.schema = schema;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (shared) {
            //需要切换
            Connection connection = switchTenantRole(sharedDataSource.getConnection(), tenant);
            //wrapper TenantConnectionProxy
            return new TenantConnectionProxy(connection, dsKey, tenant, schema);
        }
        return sharedDataSource.getConnection();
    }

    private Connection switchTenantRole(Connection connection, String tenantId) throws SQLException {
        String connectionTenant = connection.getClientInfo(TENANT_NAME);
        if (StringUtils.hasLength(connectionTenant) && connectionTenant.equals(tenantId)) {
            if (logger.isDebugEnabled()) {
                logger.debug("Reuse before connection for tenant " + tenantId + " connection " + connection);
            }
            return connection;
        }
        long sTs = System.currentTimeMillis();
        if (!StringUtils.hasText(tenantId)) {
            throw new TenantException("未找到共享数据源tenantId:"+tenantId);
        }
        //切换租户连接
        switchTenantConnection(connection, tenantId);
        if (logger.isDebugEnabled()) {
            logger.debug("tenant: " + tenantId + " switch role cost: " + (System.currentTimeMillis() - sTs) + " ms " + " connection :" + connection);
        }
        //放入到连接上到把当前租户
        Properties clientInfo = connection.getClientInfo();
        clientInfo.setProperty(TENANT_NAME, tenantId);
        return connection;
    }

    /**
     * 切换到租户的上下文
     *
     * @param connection 连接
     * @param tenant     租户
     */
    private void switchTenantConnection(Connection connection, String tenant) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("SET SESSION ROLE " + tenant);
        } catch (SQLException e) {
            throw new TenantException("switch tenant role stage: SQLException." + e);
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        if (shared) {
            Connection connection = switchTenantRole(sharedDataSource.getConnection(username, password), tenant);
            return new TenantConnectionProxy(connection, dsKey, tenant, schema);
        }
        return sharedDataSource.getConnection(username, password);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return super.isWrapperFor(iface) || iface.isInstance(sharedDataSource) || iface.isInstance(sharedDataSource);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T unwrap(Class<T> iface) {
        if (iface.isInstance(this)) {
            return (T) this;
        }
        if (iface.isInstance(sharedDataSource)) {
            return (T) sharedDataSource;
        }
        return null;
    }

    @Override
    public void close() {
        Class<? extends DataSource> clazz = sharedDataSource.getClass();
        try {
            Method closeMethod = ReflectionUtils.findMethod(clazz, "close");
            if (closeMethod != null) {
                closeMethod.invoke(sharedDataSource);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.warn("tenant-datasource close the datasource named [{}] failed,", tenant, e);
        }
    }

    public DataSource getSharedDataSource() {
        return sharedDataSource;
    }

    public boolean isShared() {
        return shared;
    }

    public String getDsKey() {
        return dsKey;
    }

    public String getSchema() {
        return schema;
    }

    public String getTenant() {
        return tenant;
    }
}
