package com.spring.boot.multitenant.common.properties;

import com.spring.boot.multitenant.common.constants.DefaultConstants;
import com.spring.boot.multitenant.common.constants.MultiTenantType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 多租户配置
 * @author lizhifu
 */
@Data
@ConfigurationProperties(prefix = DefaultConstants.MULTI_TENANT)
public class MultiTenantProperties {

    /**
     * 多租户数据源扫描包
     */
    private String[] packageToScan = new String[]{""};

    /**
     * 多租户模式
     */
    private MultiTenantType multiTenantType = MultiTenantType.COLUMN;
}
