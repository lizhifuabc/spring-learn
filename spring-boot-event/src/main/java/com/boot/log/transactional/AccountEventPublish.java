package com.boot.log.transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 如果带有 @Transactional 注解的方法失败并回滚事务，那么事件不会被发布。
 * 这是因为回滚会撤销在事务中所做的所有更改，包括事件的发布。
 *
 * @author lizhifu
 * @since 2023/9/16
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountEventPublish {
    private final ApplicationEventPublisher events;
    private final AccountRepository accountRepository;
    @Transactional(rollbackFor = Exception.class)
    public void publishWithTransactional() {
        Account account = new Account();
        account.setName("lizhifu1");
        account.setPassword("123456");

        log.info("事务发送事件");
        accountRepository.save(account);

        AccountEvent accountEvent = new AccountEvent(account);
        events.publishEvent(accountEvent);

        account = new Account();
        account.setName("lizhifu2");
        account.setPassword("123456");
        accountRepository.save(account);

    }
}
