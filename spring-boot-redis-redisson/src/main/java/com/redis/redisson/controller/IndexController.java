package com.redis.redisson.controller;

import com.redis.redisson.domain.LockParam;
import com.redis.redisson.lock.RedisLock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * IndexController
 *
 * @author lizhifu
 * @date 2020/12/10
 */
@RestController
public class IndexController {
    @Resource
    private RedisLock redisLock;
    @Resource
    private RedisTemplate redisTemplate;
    @GetMapping("/")
    public String hello(){
        ValueOperations<String, String> operation = redisTemplate.opsForValue();
        redisTemplate.opsForValue().set("lockTest", "lockTest");
        redisTemplate.delete("lockTest");

        LockParam lockParam = new LockParam();
        lockParam.setLockValue("lockTest2");
        boolean result = redisLock.lock(lockParam);
        return operation.get("lockTest");
    }
}
