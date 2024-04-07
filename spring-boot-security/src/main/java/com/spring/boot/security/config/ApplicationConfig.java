package com.spring.boot.security.config;

import com.spring.boot.security.user.CustomerUserDetailsServiceImpl;
import com.spring.boot.security.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 应用配置
 *
 * @author lizhifu
 * @since 2023/6/8
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomerUserDetailsServiceImpl();
    }
    @Bean
    public JwtTokenUtil jwtTokenUtil(){
        return new JwtTokenUtil();
    }
}
