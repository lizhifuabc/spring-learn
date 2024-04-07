package com.spring.boot.security.config;

import com.spring.boot.security.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * OncePerRequestFilter 是一个抽象类，继承自 GenericFilterBean，
 * 它的 doFilter 方法保证在一次请求只通过一次该过滤器，而不需要重复执行。
 *
 * @author lizhifu
 * @since 2024/4/7
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;

    private final UserDetailsService userDetailsService;

    public JwtAuthenticationTokenFilter(JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("进入 JwtAuthenticationTokenFilter 过滤器,校验用户是否登录,请求路径:{}",request.getRequestURI());
        String token = resolveToken(request);
        Claims claims = jwtTokenUtil.getClaims(token);
        // token 不为空,token 校验通过，并且 SecurityContextHolder 中的 Authentication 为空（表示该用户未登录）
        if (claims != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 从数据库中加载用户信息
            UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
            // 将用户信息封装到 UsernamePasswordAuthenticationToken 对象中（即：Authentication）
            // 参数：用户信息、密码（因为 JWT 令牌中没有密码，所以这里传 null）、用户权限
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            // 将请求中的详细信息（即：IP、SessionId 等）封装到 UsernamePasswordAuthenticationToken 对象中方便后续校验
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 将 UsernamePasswordAuthenticationToken 对象封装到 SecurityContextHolder 中方便后续校验
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // 放行，执行下一个过滤器
        filterChain.doFilter(request,response);
    }

    private String resolveToken(HttpServletRequest request){
        // 请求中获取 JWT 令牌的请求头（即：Authorization）
        String tokenHeader = "Authorization";
        String authHeader = request.getHeader(tokenHeader);
        // JWT 令牌前缀（即：Bearer）开头
        String tokenHead = "Bearer ";
        if (authHeader != null && authHeader.startsWith(tokenHead)){
            return authHeader.substring(tokenHead.length());
        }
        return null;
    }
}
