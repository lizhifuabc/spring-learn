package com.spring.boot.security.config;

import com.spring.boot.security.handler.RestAuthenticationEntryPoint;
import com.spring.boot.security.util.JwtTokenUtil;
import io.jsonwebtoken.lang.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Security配置
 *
 * @author lizhifu
 * @since 2023/6/8
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
@EnableConfigurationProperties(IgnoreUrlsConfig.class)
public class SecurityConfig {
    private final IgnoreUrlsConfig ignoreUrlsConfig;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    /**
     * 认证事件
     */
    private final ApplicationEventPublisher applicationEventPublisher;
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter(jwtTokenUtil,userDetailsService);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // 接口白名单
                        .requestMatchers(ignoreUrlsConfig.getAntPathRequestMatcher()).permitAll()
                        // 允许跨域请求的OPTIONS请求
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        // 所有请求需要认证
                        .anyRequest().authenticated()
                )
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
                        // 未登录或 token 过期时访问接口时自定义的返回结果
                        // 设置异常的EntryPoint，如果不设置，默认使用Http403ForbiddenEntryPoint
                        .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                )
                // 自定义 JWT 过滤器 加入到 UsernamePasswordAuthenticationFilter 之前
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // 提供自定义loadUserByUsername
        authProvider.setUserDetailsService(userDetailsService);
        // 指定密码编辑器
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    /**
     * 解决 AuthenticationManager 无法注入的问题
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> providerList = new ArrayList<>();
        providerList.add(daoAuthenticationProvider());

        ProviderManager providerManager = new ProviderManager(providerList);
        providerManager.setAuthenticationEventPublisher(new DefaultAuthenticationEventPublisher(applicationEventPublisher));

        return providerManager;
    }
    /**
     * 解决requestMatchers().permitAll()后依然执行自定义过滤器的问题
     * @return WebSecurityCustomizer
     */
    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring().requestMatchers(ignoreUrlsConfig.getAntPathRequestMatcher());
    }
}
