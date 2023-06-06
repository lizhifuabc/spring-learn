package com.redis.limit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;

import jakarta.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * RedisTest
 *
 * @author lizhifu
 * @date 2020/12/23
 */
@SpringBootTest
@Slf4j
public class RedisTest {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisScript<Long> redisLuaScript;
    @Test
    public void test(){
        String key = "index";
        int max = 2;
        int timeout = 60*2;
        List<String> keys = Collections.singletonList(key);
        for (int i = 0; i < 10; i++) {
            Long executeTimes = stringRedisTemplate.execute(redisLuaScript,keys,String.valueOf(max),String.valueOf(timeout));
            log.info("【{}】在单位时间 {} 毫秒内访问 {} 次", key,timeout, executeTimes);
        }
        String value  = stringRedisTemplate.opsForValue().get("index");
        System.out.println(value);
    }
}
