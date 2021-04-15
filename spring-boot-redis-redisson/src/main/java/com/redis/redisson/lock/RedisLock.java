package com.redis.redisson.lock;

import com.redis.redisson.domain.LockParam;
import lombok.extern.slf4j.Slf4j;
import org.redisson.RedissonLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * redis锁
 *
 * @author lizhifu
 * @date 2020/12/10
 */
@Component
@Slf4j
public class RedisLock {
    @Resource
    private RedissonClient redissonClient;
    /**
     * 可重入锁
     * @param lockParam
     * @return
     */
    public boolean lock(LockParam lockParam) {
        try {
            RLock lock = redissonClient.getLock(lockParam.getLockValue());
            lock.lock(lockParam.getTimeout(),lockParam.getTimeUnit());
            log.info("Thread [{}] RedisLock lock [{}] success", Thread.currentThread().getName(), lockParam.toString());
            // 加锁成功
            return true;
        } catch (Exception e) {
            log.error("RedisLock lock [{}] Exception:", lockParam.toString(), e);
            return false;
        }
    }

    /**
     * 是否锁
     * @param lockValue 加锁值
     * @return
     */
    public Boolean unlock(String lockValue) {
        try {
            RLock lock = redissonClient.getLock(lockValue);
            lock.unlock();
            log.info("Thread [{}] RedisLock unlock [{}] success", Thread.currentThread().getName(), lockValue);
            // 释放锁成功
            return true;
        } catch (Exception e) {
            log.error("RedisLock unlock [{}] Exception:", lockValue, e);
            return false;
        }
    }
}
