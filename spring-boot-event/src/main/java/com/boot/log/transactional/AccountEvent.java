package com.boot.log.transactional;

import org.springframework.context.ApplicationEvent;

/**
 * TODO
 *
 * @author lizhifu
 * @since 2023/9/17
 */
public class AccountEvent extends ApplicationEvent {

    public AccountEvent(Account account) {
        super(account);
    }
}
