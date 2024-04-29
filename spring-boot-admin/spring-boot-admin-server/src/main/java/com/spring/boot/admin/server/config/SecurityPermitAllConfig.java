package com.spring.boot.admin.server.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;

/**
 * 安全配置
 *
 * @author lizhifu
 * @since 2024/4/29
 */
public class SecurityPermitAllConfig {
    private final AdminServerProperties adminServer;

    public SecurityPermitAllConfig(AdminServerProperties adminServer) {
        this.adminServer = adminServer;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authorizeRequest) -> authorizeRequest.anyRequest().permitAll());

        http.addFilterAfter(new CustomCsrfFilter(), BasicAuthenticationFilter.class)
                .csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher(this.adminServer.path("/instances"), POST.toString()),
                                new AntPathRequestMatcher(this.adminServer.path("/notifications/**"), POST.toString()),
                                new AntPathRequestMatcher(this.adminServer.path("/notifications/**"), DELETE.toString()),
                                new AntPathRequestMatcher(this.adminServer.path("/instances/*"), DELETE.toString()),
                                new AntPathRequestMatcher(this.adminServer.path("/actuator/**"))));

        return http.build();

    }
}
