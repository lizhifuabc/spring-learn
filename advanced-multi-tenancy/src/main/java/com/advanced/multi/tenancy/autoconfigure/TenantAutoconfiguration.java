package com.advanced.multi.tenancy.autoconfigure;

import com.advanced.multi.tenancy.properties.TenantProperties;
import com.advanced.multi.tenancy.redis.TenantRedisConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * 多租户自动配置
 *
 * @author lizhifu
 * @since 2023/7/16
 */
@AutoConfiguration
@Import({DataSourceCreatorConfig.class})
@EnableConfigurationProperties({TenantProperties.class
, TenantRedisConfig.class}) // 开启配置属性支持
@AutoConfigureBefore(value = DataSourceAutoConfiguration.class) // 在DataSourceAutoConfiguration之前加载
@ConditionalOnProperty(prefix = TenantProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true) // 当配置文件中的spring.multi.tenancy.enabled=true时，自动配置生效
public class TenantAutoconfiguration {
    private final TenantProperties properties;

    public TenantAutoconfiguration(TenantProperties properties) {
        this.properties = properties;
    }
}
