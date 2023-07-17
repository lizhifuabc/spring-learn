package com.advanced.multi.tenancy.datasource;

import com.advanced.multi.tenancy.constants.CommonConstants;
import com.advanced.multi.tenancy.datasource.provider.TenantDataSourceProvider;
import com.advanced.multi.tenancy.datasource.proxy.TenantDataSourceProxy;
import com.advanced.multi.tenancy.properties.DataSourceDetailProperties;
import com.advanced.multi.tenancy.support.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 核心数据源切换
 *
 * @author lizhifu
 * @since 2023/7/16
 */
@Slf4j
public class TenantDynamicRoutingDataSource extends AbstractDataSource
        implements TenantDataSourceRegister, InitializingBean, DisposableBean {
    private final Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();
    @Override
    public boolean register(DataSourceDetailProperties dataSourceDetailProperties) {
        return false;
    }

    @Override
    public boolean remove(String tenant) {
        dataSourceMap.remove(tenant);
        return true;
    }

    @Override
    public Connection getConnection() throws SQLException {
        DataSource dataSource = determineDataSource();
        return dataSource.getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        DataSource dataSource = determineDataSource();
        return dataSource.getConnection(username, password);
    }

    @Override
    public void destroy() throws Exception {
        log.info("多租户数据源开始关闭 ....");
        for (Map.Entry<String, DataSource> item : dataSourceMap.entrySet()) {
            closeDataSource(item.getKey(), item.getValue());
        }
        log.info("多租户数据源关闭完成 ....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化所有数据
        Map<String, DataSource> dataSources = new HashMap<>(16);
        for (TenantDataSourceProvider provider : tenantDataSourceProvider) {
            dataSources.putAll(provider.loadDataSources());
        }

        //添加到数据源中
        for (Map.Entry<String, DataSource> dsItem : dataSources.entrySet()) {
            addDataSource(dsItem.getKey(), dsItem.getValue());
        }
    }

    /**
     * 获取数据源
     * @return 数据源
     */
    private DataSource determineDataSource() {
        String dsKey = TenantContextHolder.peek();
        return getDataSource(dsKey);
    }
    /**
     * 获取数据源
     *
     * @param ds 数据源名称
     * @return 数据源
     */
    public DataSource getDataSource(String ds) {
        if (!StringUtils.hasLength(ds)) {
            return defaultDataSource();
        }
        return dataSourceMap.get(ds);
    }
    /**
     * 获取默认数据源
     *
     * @return def 数据源
     */
    private DataSource defaultDataSource() {
        return dataSourceMap.get(CommonConstants.DEF);
    }

    /**
     * close db
     *
     * @param ds         dsName
     * @param dataSource db
     */
    private void closeDataSource(String ds, DataSource dataSource) {
        if (dataSource instanceof TenantDataSourceProxy proxy) {
            if (!proxy.isShared()) {
                ((TenantDataSourceProxy) dataSource).close();
            }
        } else {
            Method closeMethod = ReflectionUtils.findMethod(dataSource.getClass(), "close");
            if (closeMethod != null) {
                try {
                    closeMethod.invoke(dataSource);
                } catch (Exception e) {
                    log.warn("数据源 [{}] 关闭失败", ds, e);
                }
            }
        }
    }
}
