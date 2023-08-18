package com.boot.casbin.config;

import com.boot.casbin.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.nio.charset.StandardCharsets;

/**
 * security配置
 *
 * @author lizhifu
 * @since 2023/8/18
 */
@EnableWebSecurity
@Configuration
@Slf4j
public class SecurityConfiguration {
    private final Enforcer enforcer;
    private final UserService userService;
    private final String[] permitAll = new String[]{
            "/auth/**",
            "/favicon.ico",
            "/error/**",
    };
    @Inject
    public SecurityConfiguration(Enforcer enforcer, UserService userService) {
        this.enforcer = enforcer;
        this.userService = userService;
    }

    /**
     * 忽略的请求
     * 添加自定义过滤器的时候，Security先认证后授权，permitAll是授权部分，所以要在这里忽略
     * @return WebSecurityCustomizer 忽略的请求
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(permitAll);
    }
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("安全配置，securityFilterChain");
        http
                // 关闭csrf,允许跨域
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers(permitAll).permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(new CasbinFilter(enforcer, userService), BasicAuthenticationFilter.class)
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
                        .authenticationEntryPoint((request, response, authException) -> {
                            log.warn("未授权", authException);
                            exceptionHandling(response,"未授权");
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            log.warn("权限不足", accessDeniedException);
                            exceptionHandling(response,"权限不足");
                        }));
        return http.build();
    }
    private static void exceptionHandling(HttpServletResponse response, String resp){
        try {
            response.setContentType(MediaType.APPLICATION_JSON.toString());
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.getWriter().print(resp);
            response.getWriter().flush();
            response.getWriter().close();
        }catch (Exception e){
            log.error("exceptionHandling",e);
        }
    }
}
