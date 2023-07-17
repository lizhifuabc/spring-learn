package com.advanced.multi.tenancy.properties;

import com.advanced.multi.tenancy.constants.CommonConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 多租户配置文件
 *
 * @author lizhifu
 * @since 2023/7/16
 */
@Data
@ConfigurationProperties(prefix = TenantProperties.PREFIX)
public class TenantProperties {
    public static final String PREFIX = "spring.multi.tenancy";
    /**
     * 默认数据库default
     */
    private String primary = CommonConstants.DEF;
    /**
     * 是否开启多租户模式
     */
    private boolean enabled;

    /**
     * 数据源配置
     */
    private Map<String, DataSourceDetailProperties> datasource = new LinkedHashMap<>();
}
