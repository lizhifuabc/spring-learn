package com.redis.advanced.configure;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.redis.advanced.service.BloomFilterHelper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * LettuceRedisConfig：配置
 *
 * @author lizhifu
 * @date 2020/12/4
 */
@Configuration
@EnableCaching
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class LettuceRedisConfig {
    /**
     * 默认情况下的模板只能支持RedisTemplate<String, String>，需要自定义 RedisTemplate
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    /**
     * 配置使用注解的时候缓存配置，默认是序列化反序列化的形式，加上此配置则为 json 形式
     * @param factory
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        // 配置序列化
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        RedisCacheConfiguration redisCacheConfiguration = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager.builder(factory).cacheDefaults(redisCacheConfiguration).build();
    }
    @Bean
    public BloomFilterHelper<String> initBloomFilterHelper() {
        return new BloomFilterHelper<>((Funnel<String>) (from, into) ->
                into.putString(from, Charsets.UTF_8).putString(from, Charsets.UTF_8),
                1000000, 0.01);
    }
}