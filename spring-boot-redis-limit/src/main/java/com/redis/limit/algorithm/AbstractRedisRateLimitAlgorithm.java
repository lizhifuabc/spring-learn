package com.redis.limit.algorithm;

import java.util.Arrays;
import java.util.List;

/**
 * 限流算法
 *
 * @author lizhifu
 * @date 2021/4/30
 */
public abstract class AbstractRedisRateLimitAlgorithm {
    /**
     * 限流脚本名称
     * @return String
     */
    public abstract String getScriptName();

    /**
     * 限流key名称
     * @return String
     */
    public abstract String getKeyName();

    /**
     * 获取限流key list
     * @param id 限流标识
     * @return List
     */
    public List<String> getKeys(final String id) {
        String prefix = getKeyName() + ".{" + id;
        String tokenKey = prefix + "}.tokens";
        String timestampKey = prefix + "}.timestamp";
        return Arrays.asList(tokenKey, timestampKey);
    }
}
