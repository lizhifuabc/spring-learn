package com.redis.limit.algorithm;


/**
 * 令牌桶算法
 *
 * @author lizhifu
 * @date 2021/4/30
 */
public class TokenBucketRedisRateLimitAlgorithm extends AbstractRedisRateLimitAlgorithm{
    @Override
    public String getScriptName() {
        return "tokenBucket.lua";
    }

    @Override
    public String getKeyName() {
        return "token_bucket_rate_limit";
    }
}
