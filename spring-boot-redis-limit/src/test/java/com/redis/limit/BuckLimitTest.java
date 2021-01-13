package com.redis.limit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * 令牌桶测试
 *
 * @author lizhifu
 * @date 2021/1/13
 */
@SpringBootTest
public class BuckLimitTest {
    /**
     * 限流的key
     */
    private static final String key = "ratelimit:test";
    /**
     * 应用名称：方便数据统计
     */
    private static final String app = "spring-boot-redis";
    /**
     * 当前时间:首次获取时间(单位为毫秒)
     */
    private static Long currMillSecond = System.currentTimeMillis();
    /**
     * 当前可用的令牌
     */
    private static final String currPermits = "1";
    /**
     * 桶最大数量
     */
    private static final String maxBurst = "10";
    /**
     * 获得系统的时间，单位为毫秒,转换为妙
     * ((当前时间 -上次获取时间) / 1000) * rate = 应该生成的令牌数量
     * 每秒生成几个令牌
     */
    private static final String rate = "10";
    /**
     * 需要的令牌数量
     */
    private static final String permits = "1";
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisScript<Long> bucketRedisLuaScript;
    @Resource
    private RedisScript<Long> bucketInitRedisLuaScript;
    @Test
    public void test() throws InterruptedException {
        Long init = stringRedisTemplate.execute(bucketInitRedisLuaScript,
                Collections.singletonList(key), currMillSecond.toString(), currPermits, maxBurst, rate, app);
        System.out.println(init);
        Thread.sleep(1000L);
        for (int i = 0; i < 10; i++) {
            currMillSecond = System.currentTimeMillis();
            Long accquire = stringRedisTemplate.execute(bucketRedisLuaScript,
                    Collections.singletonList(key),permits, currMillSecond.toString());
            System.out.println("获取次数"+i+"结果："+accquire);
        }

        //获取hash结构中所有的键值对(hgetall)
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(key);
        for(Map.Entry<Object, Object> entry : entries.entrySet()){
            System.out.println(entry.getKey()+"======"+entry.getValue());
        }
    }
}
