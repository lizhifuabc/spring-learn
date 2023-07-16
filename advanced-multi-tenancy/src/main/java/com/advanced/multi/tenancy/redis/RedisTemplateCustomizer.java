package com.advanced.multi.tenancy.redis;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * redisTemplate 自定义
 * 对不同的RedisTemplate实例进行定制化配置,而不需要每次都重复相同的设置代码。
 *
 * @author lizhifu
 * @since 2023/7/16
 */
public interface RedisTemplateCustomizer {
    /**
     * redisTemplate 自定义
     *
     * @param redisTemplate RedisTemplate<String, Object>
     */
    void customize(RedisTemplate<String, Object> redisTemplate);
}
