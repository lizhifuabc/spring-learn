package com.advanced.multi.tenancy.properties;

import com.advanced.multi.tenancy.constants.SeataModeType;
import com.advanced.multi.tenancy.constants.TenantType;
import com.zaxxer.hikari.HikariConfig;
import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * 多租户配置文件
 *
 * @author lizhifu
 * @since 2023/7/16
 */
@Data
public class DataSourceDetailProperties {
    /**
     * 连接池名称
     */
    private String poolName;
    /**
     * 连接池类型 Druid > HikariCp
     */
    private Class<? extends DataSource> type;
    /**
     * driver
     */
    private String driverClassName;
    /**
     * url
     */
    private String url;
    /**
     * username
     */
    private String username;
    /**
     * password
     */
    private String password;
    /**
     * 是否启用seata
     */
    private Boolean seata = false;
    /**
     * seata使用模式，默认AT
     */
    private SeataModeType seataModeType = SeataModeType.AT;

    /**
     * 是否启用p6spy
     */
    private Boolean p6spy = false;
    /**
     * 懒加载数据源 TODO
     */
    private Boolean lazy;

    /**
     * 租户模式
     */
    private TenantType tenantType = TenantType.EXCLUSIVE;

    /**
     * 租户列表
     */
    private List<String> tenants = new ArrayList<>();

    /**
     * HikariCp 配置
     */
    @NestedConfigurationProperty
    private HikariConfig hikari = new HikariConfig();
    /**
     * jndi数据源名称
     */
    private String jndiName;
}
