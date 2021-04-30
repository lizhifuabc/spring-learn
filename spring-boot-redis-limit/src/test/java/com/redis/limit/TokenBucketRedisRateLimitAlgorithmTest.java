package com.redis.limit;

import com.redis.limit.algorithm.AbstractRedisRateLimitAlgorithm;
import com.redis.limit.algorithm.TokenBucketRedisRateLimitAlgorithm;

/**
 * TokenBucketRedisRateLimitAlgorithm
 *
 * @author lizhifu
 * @date 2021/4/30
 */
public class TokenBucketRedisRateLimitAlgorithmTest {
    public static void main(String[] args) {
        AbstractRedisRateLimitAlgorithm algorithm = new TokenBucketRedisRateLimitAlgorithm();
        System.out.println(algorithm.getKeys("asdfasdf").toString());
    }
}
