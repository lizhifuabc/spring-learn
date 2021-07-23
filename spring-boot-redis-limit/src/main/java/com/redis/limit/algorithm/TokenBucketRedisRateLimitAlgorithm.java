package com.redis.limit.algorithm;


/**
 * 令牌桶算法
 * key[1] token
 * key[2] timestamp
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
