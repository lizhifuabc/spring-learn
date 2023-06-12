package com.spring.boot.multitenant.hibernate;

import com.spring.boot.multitenant.context.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 租户选择器
 * 数据库请求发生时。使用 CurrentTenantIdentifierResolver （租户ID解析器）接口获取这一信息
 * @author lizhifu
 * @since 2023/6/12
 */
@Component
@Slf4j
public class TenantIdResolver implements CurrentTenantIdentifierResolver, HibernatePropertiesCustomizer {
    @Override
    public String resolveCurrentTenantIdentifier() {
        String currentTenantId = TenantContextHolder.getTenantId();
        log.info("租户选择器,当前租户为:{}",currentTenantId);
        return currentTenantId;
    }

    /**
     * 校验当前线程是否存在已经打开的Session,默认为true。
     * 同一线程中打开了两个Session,由于validateExistingCurrentSessions为true,Hibernate会检测到并抛出异常。
     * @return boolean 是否校验（确保已经存在的 Session 都有一个对应的 Tenant ID）
     */
    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, this);
    }
}
