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
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (authenticate == null) {
            log.error("{username: {}, password: {}} 认证失败！", username, password);
            return null;
        }
        // 根据用户名从数据库中获取用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // 封装用户信息（由于使用 JWT 进行验证，这里不需要凭证）
        UsernamePasswordAuthenticationToken authentication
                = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        // 将用户信息存储到 Security 上下文中，以便于 Security 进行权限验证
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成 token
        return jwtTokenUtil.generateToken(username,userDetails.getAuthorities());
    }
}
