package com.spring.boot.multitenant.base;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 仓储类
 *
 * @author lizhifu
 * @since 2023/6/12
 */
public interface SysTenantRepository extends JpaRepository<SysTenant,Long> {
}
