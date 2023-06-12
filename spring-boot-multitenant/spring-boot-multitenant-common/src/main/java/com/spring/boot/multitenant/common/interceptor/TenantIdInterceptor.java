package com.spring.boot.multitenant.common.interceptor;

import com.spring.boot.multitenant.common.constants.DefaultConstants;
import com.spring.boot.multitenant.common.context.TenantContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 多租户拦截器
 *
 * @author lizhifu
 * @since 2023/6/12
 */
@Slf4j
public class TenantIdInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("设置租户ID:{}",request.getHeader(DefaultConstants.DEFAULT_HEADER_TENANT_ID));
        TenantContextHolder.setTenantId(request.getHeader(DefaultConstants.DEFAULT_HEADER_TENANT_ID));
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("清除租户ID:{}",request.getRequestURI());
        TenantContextHolder.clear();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
