package com.learn.redis;

import com.learn.redis.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;

/**
 * RedisService测试
 *
 * @author lizhifu
 * @date 2020/12/4
 */
@SpringBootTest
public class RedisServiceTest {
    @Resource
    RedisService redisService;
    @Test
    public void test(){
        redisService.setCacheObject("Key","Value");
        String value = redisService.getCacheObject("Key");
        System.out.println(value);
    }

}
