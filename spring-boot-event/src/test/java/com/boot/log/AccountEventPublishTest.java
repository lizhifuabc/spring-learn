package com.boot.log;

import com.boot.log.transactional.AccountEventPublish;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO
 *
 * @author lizhifu
 * @since 2023/9/16
 */
@SpringBootTest
public class AccountEventPublishTest {
    @Resource
    AccountEventPublish accountEventPublish;
    @Test
    public void publishWithTransactional() {
        accountEventPublish.publishWithTransactional();
    }
}
