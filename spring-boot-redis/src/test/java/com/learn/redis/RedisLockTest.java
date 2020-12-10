package com.learn.redis;

import com.learn.redis.lock.RedisLock;
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
    public void test(){
        long time = System.currentTimeMillis() + 5*1000;
        for (int i = 0; i < 10; i++) {
            boolean result =  redisLock.lock("test",String.valueOf(time));
            System.out.println("加锁状态"+result+Thread.currentThread().getName());
            redisLock.releaseLock("test",String.valueOf(time));
        }
    }
}
