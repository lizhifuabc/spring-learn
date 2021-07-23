package com.redis.limit;

import com.redis.limit.algorithm.AbstractRedisRateLimitAlgorithm;
import com.redis.limit.algorithm.TokenBucketRedisRateLimitAlgorithm;

import java.util.List;

/**
 * TokenBucketRedisRateLimitAlgorithm
 *
 * @author lizhifu
 * @date 2021/4/30
 */
public class TokenBucketRedisRateLimitAlgorithmTest {
    public static void main(String[] args) {
        String id = "tokentest";
        AbstractRedisRateLimitAlgorithm algorithm = new TokenBucketRedisRateLimitAlgorithm();

        List<String> keys = algorithm.getKeys(id);
        System.out.println(algorithm.getKeys("asdfasdf").toString());
    }
}
