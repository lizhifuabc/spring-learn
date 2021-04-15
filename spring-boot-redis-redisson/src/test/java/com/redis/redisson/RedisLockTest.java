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
        boolean lock = redisLock.lock(lockParam);
        System.out.println("加锁结果"+lock);
        for (int i = 0; i < 5; i++) {
            lock = redisLock.lock(lockParam);
            System.out.println("同一个线程继续加锁："+i);
            System.out.println("同一个线程继续加锁："+lock);
        }
        Thread.sleep(1000*5);
    }
    @Test
    public void test2() throws InterruptedException {
        LockParam lockParam = new LockParam();
        lockParam.setLockValue("lockTest");
        boolean lock = redisLock.lock(lockParam);
        System.out.println("加锁结果"+lock);
        for (int i = 0; i < 5; i++) {
            lock = redisLock.lock(lockParam);
            System.out.println("同一个线程继续加锁："+i);
            System.out.println("同一个线程继续加锁："+lock);
        }
        new Thread(()->{
            System.out.println("另一个线程加锁");
            boolean other = redisLock.lock(lockParam);
            System.out.println("另一个线程加锁："+other);
        }).start();
        Thread.sleep(1000*5);
    }
}
