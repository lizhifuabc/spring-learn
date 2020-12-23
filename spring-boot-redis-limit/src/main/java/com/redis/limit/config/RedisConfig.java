package com.redis.limit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

/**
 * Redis 配置
 *
 * @author lizhifu
 * @date 2020/12/22
 */
@Configuration
public class RedisConfig {
    /**
     * 读取限流脚本
     */
    @Bean
    public DefaultRedisScript<Long> redisLuaScript() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/limit.lua")));
        redisScript.setResultType(Long.class);
        return redisScript;
    }
}
