package com.boot.idempotent.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * redis 锁
 * 获取当前时间戳，单位是毫秒；
 * <li>轮流尝试在每个 master 节点上创建锁，过期时间较短，一般就几十毫秒；
 * <li>尝试在大多数节点上建立一个锁，比如 5 个节点就要求是 3 个节点 n / 2 + 1 ；
 * <li>客户端计算建立好锁的时间，如果建立锁的时间小于超时时间，就算建立成功了；
 * <li>要是锁建立失败了，那么就依次之前建立过的锁删除；
 * <li>只要别人建立了一把分布式锁，你就得不断轮询去尝试获取锁。
 * @author lizhifu
 * @date 2020/12/10
 */
@Component
public class RedisLock {
    @Resource
    public RedisTemplate redisTemplate;
    /**
     * 加锁
     * @param lockKey 加锁的Key
     * @param timeStamp 过期时间：当前时间+超时时间
     * @return
     */
    public boolean lock(String lockKey, String timeStamp) {
        // 对应setnx命令，可以成功设置,也就是key不存在，获得锁成功
        if (redisTemplate.opsForValue().setIfAbsent(lockKey, timeStamp)) {
            return true;
        }
        // 获取锁视频，进一步判断锁是否超时，防止出现死锁
        String currentLock = (String) redisTemplate.opsForValue().get(lockKey);
        // 锁超时 currentLock不为空且小于当前时间
        if(StringUtils.isNotEmpty(currentLock) && Long.parseLong(currentLock) < System.currentTimeMillis()){
            //如果lockKey对应的锁已经存在，获取上一次设置的时间戳之后并重置lockKey对应的锁的时间戳
            //设置lockKey的value并返回其旧值
            //此时线程A和B并发进行，但是获取的currentLock是一样的
            //但是preLock获取的数值是不一样的，因为getAndSet一次只会一个执行
            //所以只有一个线程能够真正的获取到原始的currentLock
            String preLock = (String) redisTemplate.opsForValue().getAndSet(lockKey, timeStamp);
            if(StringUtils.isNotBlank(preLock)  && preLock.equals(currentLock)){
                return true;
            }
        }
        return false;
    }
    /**
     * 释放锁
     * @param lockKey
     * @param timeStamp
     */
    public void releaseLock(String lockKey,String timeStamp){
        String currentValue = (String) redisTemplate.opsForValue().get(lockKey);
        if(StringUtils.isNotBlank(currentValue) && currentValue.equals(timeStamp) ){
            // 删除锁
            redisTemplate.opsForValue().getOperations().delete(lockKey);
        }
    }
}
