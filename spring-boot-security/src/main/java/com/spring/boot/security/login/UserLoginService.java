package com.spring.boot.security.login;

import com.spring.boot.security.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * 登录
 *
 * @author lizhifu
 * @since 2024/4/7
 */
@Service
@Slf4j
public class UserLoginService {
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    public UserLoginService(UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    public String login(String username, String password) {
        // 构造用户名密码认证信息
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        // 对认证信息进行认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (authenticate == null) {
            log.error("{username: {}, password: {}} 认证失败！", username, password);
            return null;
        }
        // 将用户信息存储到 Security 上下文中，以便于 Security 进行权限验证
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        // 生成 token
        return jwtTokenUtil.generateToken(username,authenticate.getAuthorities());
    }
}
