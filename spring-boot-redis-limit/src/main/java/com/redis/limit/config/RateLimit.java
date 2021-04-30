package com.redis.limit.config;

import lombok.Data;

/**
 * 限流配置
 * @author lizhifu
 */
@Data
public class RateLimit {
    
    /**
     * 算法名称
     */
    private String algorithmName;

    /**
     * 发牌速率
     */
    private double replenishRate;

    /**
     * 令牌桶容量
     */
    private double burstCapacity;
    
    /**
     * 请求数量
     */
    private double requestCount = 1.0;
}
