package com.spring.boot.security.user;

import com.spring.boot.security.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

/**
 * 自定义 UserDetailsService
 *
 * @author lizhifu
 * @since 2023/6/8
 */
@Slf4j
@Primary
@RequiredArgsConstructor
public class CustomerUserDetailsServiceImpl implements CustomerUserDetailsService{
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("自定义 UserDetailsService,username:{}",username);
        // 构造security用户
        Collection<GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList("ROLE_ADMIN");
        return new CustomerUser(username, SecurityConstants.BCRYPT + "$2a$10$RBk/Ib3UjCN7LHqpqJLSmejCF2PzL7tJ7LZy48jaDgldE19S991Ci", authorities);
    }
}
