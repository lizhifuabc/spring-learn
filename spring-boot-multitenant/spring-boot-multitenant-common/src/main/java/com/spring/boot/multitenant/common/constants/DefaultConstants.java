package com.spring.boot.multitenant.common.constants;

/**
 * 默认常量
 *
 * @author lizhifu
 * @since 2023/6/12
 */
public interface DefaultConstants {
    /**
     * 默认租户ID
     */
    String DEFAULT_TENANT_ID = "public";
    /**
     * 默认header中租户ID
     */
    String DEFAULT_HEADER_TENANT_ID = "x-tenant-id";
    /**
     * 属性前缀
     */
    String MULTI_TENANT = "tomato.multi-tenant";
}
