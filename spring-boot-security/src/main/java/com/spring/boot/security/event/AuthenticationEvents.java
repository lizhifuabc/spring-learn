package com.spring.boot.security.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * 认证事件处理
 *
 * @author lizhifu
 * @since 2024/4/3
 */
@Component
@AllArgsConstructor
@Slf4j
public class AuthenticationEvents {
    @EventListener
    public void onSuccess(AuthenticationSuccessEvent event) {
        log.info("认证成功:{}",event.getAuthentication().getPrincipal());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent event) {
        log.info("认证失败:{}",event.getAuthentication().getPrincipal());
    }
}
