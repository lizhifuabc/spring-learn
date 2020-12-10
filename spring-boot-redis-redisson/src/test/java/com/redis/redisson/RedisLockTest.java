package com.redis.redisson;

import com.redis.redisson.domain.LockParam;
import com.redis.redisson.lock.RedisLock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * RedisLock 测试
 *
 * @author lizhifu
 * @date 2020/12/10
 */
@SpringBootTest
public class RedisLockTest {
    @Resource
    RedisLock redisLock;
    @Test
    public void test() throws InterruptedException {
        LockParam lockParam = new LockParam();
        lockParam.setLockValue("lockTest");
        for (int i = 0; i < 5; i++) {
            final int j = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("开始加锁"+j);
                    boolean lock = redisLock.lock(lockParam);
                    System.out.println("加锁结果"+lock);
                    redisLock.unlock(lockParam.getLockValue());
                    System.out.println("解锁"+lock);
                }
            }).start();
        }
        Thread.sleep(1000*5);
    }
}
