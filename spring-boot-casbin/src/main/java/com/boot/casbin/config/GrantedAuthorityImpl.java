package com.boot.casbin.config;

import org.springframework.security.core.GrantedAuthority;

/**
 * 权限
 *
 * @author lizhifu
 * @since 2023/8/18
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
    private final String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
