package com.boot.casbin.config;

import com.boot.casbin.model.User;
import com.boot.casbin.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * 授权拦截器
 *
 * @author lizhifu
 * @since 2023/8/18
 */
@Slf4j
public class CasbinFilter extends OncePerRequestFilter {
    private final Enforcer enforcer;
    private final UserService userService;

    public CasbinFilter(Enforcer enforcer, UserService userService) {
        this.enforcer = enforcer;
        this.userService = userService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String sessionId = httpServletRequest.getSession(true).getId();
        Optional<User> u = userService.isAuthenticated(sessionId);
        if (u.isPresent()) {
            String user = u.get().getUsername();
            String method = httpServletRequest.getMethod();
            String path = httpServletRequest.getRequestURI();
            if (enforcer.enforce(user, path, method)) {
                log.info("会话已授权: {} {} {} {}", sessionId, user, method, path);
                List<String> rolesForUser = enforcer.getRolesForUser(user);
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(new AuthenticationImpl(u.get().getUsername(), rolesForUser));
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
                chain.doFilter(httpServletRequest, response);
            } else {
                log.error("会话未经授权: {} {} {} {}", sessionId, user, method, path);
                response.setStatus(HttpStatus.FORBIDDEN.value());
            }
        } else {
            log.error("用户不存在会话未经授权: {}", sessionId);
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }
}
