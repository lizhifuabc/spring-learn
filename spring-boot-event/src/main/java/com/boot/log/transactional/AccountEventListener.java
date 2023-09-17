package com.boot.log.transactional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 监听事件
 *
 * @author lizhifu
 * @since 2023/9/17
 */
@Slf4j
@Component
public class AccountEventListener {
    @Async
    @TransactionalEventListener(value = AccountEvent.class,phase = TransactionPhase.AFTER_COMMIT)
    public void onApplicationEvent(AccountEvent event) {
        log.info("AccountEventListener 监听事件:{}",event.getSource());
        throw new RuntimeException("AccountEventListener");
    }
}
