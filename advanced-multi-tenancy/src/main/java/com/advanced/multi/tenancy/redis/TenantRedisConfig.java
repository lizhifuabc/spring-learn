package com.advanced.multi.tenancy.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 多租户Redis配置
 *
 * @author lizhifu
 * @since 2023/7/16
 */
@Configuration
@Slf4j
public class TenantRedisConfig {
    /**
     * 无租户标识的redisTemplate
     *
     * @param redisConnectionFactory RedisConnectionFactory
     * @param customizers            customizers
     * @return RedisTemplate<String, Object>
     */
    @Bean("noneTenantRedisTemplate")
    public RedisTemplate<String, Object> noneTenantRedisTemplate(RedisConnectionFactory redisConnectionFactory,
                                                                 ObjectProvider<RedisTemplateCustomizer> customizers) {
        RedisTemplate<String, Object> redisTemplate = redisTemplate(redisConnectionFactory, customizers);
        // key 序列化，不加入租户标识
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        log.info("TenantRedisConfig|无租户标识的redisTemplate|配置");
        return redisTemplate;
    }

    /**
     * 解析租户标识的 RedisTemplate
     *
     * @param redisConnectionFactory RedisConnectionFactory
     * @param customizers            ObjectProvider<RedisTemplateCustomizer>
     * @return RedisTemplate<String, Object>
     */
    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory,
                                                       ObjectProvider<RedisTemplateCustomizer> customizers) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // key 序列化，加入租户标识
        redisTemplate.setKeySerializer(new TenantPrefixStringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        customizers.orderedStream().forEach((customizer) -> customizer.customize(redisTemplate));
        log.info("TenantRedisConfig|解析租户标识的redisTemplate|配置");
        return redisTemplate;
    }

    /**
     * 无租户标识的stringRedisTemplate
     *
     * @param redisConnectionFactory RedisConnectionFactory
     * @return StringRedisTemplate 不解析租户标识的stringRedisTemplate
     */
    @Bean("noneTenantStringRedisTemplate")
    public StringRedisTemplate noneTenantStringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = stringRedisTemplate(redisConnectionFactory);
        // key 序列化，不加入租户标识
        stringRedisTemplate.setKeySerializer(RedisSerializer.string());
        log.info("TenantRedisConfig|无租户标识的stringRedisTemplate|配置");
        return stringRedisTemplate;
    }

    /**
     * 解析租户标识的 StringRedisTemplate
     *
     * @param redisConnectionFactory RedisConnectionFactory
     * @return StringRedisTemplate  解析租户标识的 StringRedisTemplate
     */
    @Bean("stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory);
        // key 序列化，加入租户标识
        stringRedisTemplate.setKeySerializer(new TenantPrefixStringRedisSerializer());
        stringRedisTemplate.setValueSerializer(RedisSerializer.string());
        stringRedisTemplate.setHashKeySerializer(RedisSerializer.string());
        stringRedisTemplate.setHashValueSerializer(RedisSerializer.string());
        log.info("TenantRedisConfig|解析租户标识的stringRedisTemplate|配置");
        return stringRedisTemplate;
    }

    /**
     * spring redis cache 适配
     *
     * @param cacheProperties {@link CacheProperties}
     * @return {@link RedisCacheConfiguration}
     */
    @Bean
    @ConditionalOnProperty(name = "spring.cache.type", havingValue = "redis")
    public RedisCacheConfiguration createConfiguration(CacheProperties cacheProperties) {
        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();

        //key 序列化,加入租户标识
        config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new TenantPrefixStringRedisSerializer()));
        //value 序列化
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            config = config.prefixCacheNameWith(redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }
        log.info("TenantRedisConfig|spring redis cache 适配|配置:{}", config);
        return config;
    }
}
