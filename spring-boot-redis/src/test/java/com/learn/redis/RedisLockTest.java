package com.learn.redis;

import com.learn.redis.lock.RedisLock;
import com.learn.redis.service.RedisService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

/**
 * RedisLock 测试
 *
 * @author lizhifu
 * @date 2020/12/10
 */
@SpringBootTest
public class RedisLockTest {
    @Resource
    RedisService redisService;
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
    @Test
    public void releaseLockLua(){
        Date date = DateUtils.addDays(new Date(),3);
        long time = 1609142590428L;
        boolean result =  redisLock.lock("test",String.valueOf(time));
        System.out.println(result);
        System.out.println((String) redisService.getCacheObject("test"));
        redisLock.releaseLockLua("test",String.valueOf("12123123"));
    }
    @Test
    public void deleteKey(){
        redisService.deleteObject("test");
    }
}
