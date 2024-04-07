package com.spring.boot.security.user;

import org.springframework.core.Ordered;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 自定义 UserDetailsService
 *
 * @author lizhifu
 * @since 2023/6/8
 */
public interface CustomerUserDetailsService extends UserDetailsService, Ordered {
    /**
     * 排序值 默认取最大的
     * @return 排序值
     */
    @Override
    default int getOrder() {
        return Integer.MIN_VALUE;
    }
}
