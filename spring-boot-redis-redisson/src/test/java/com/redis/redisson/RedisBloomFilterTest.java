package com.redis.redisson;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;

/**
 * RedisBloomFilter
 * TODO 原理解析
 * @author lizhifu
 * @date 2021/1/14
 */
@SpringBootTest
public class RedisBloomFilterTest {
    @Resource
    private RedissonClient redissonClient;
    @Test
    public void test(){
        // 获取布隆过滤器
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("userList");
        //初始化布隆过滤器：预计元素为1wL,误差率为0.01
        bloomFilter.tryInit(10000,0.01);

        for (int i = 0; i < 10000; i++) {
            bloomFilter.add("testtest" + i);
        }
        int count = 0;
        for (int i = 0; i < 100; i++) {
            if (bloomFilter.contains("testtest" + i)) {
                count++;
            }
        }
        // 匹配数量 100
        System.out.println("匹配数量 " + count);
    }
}
