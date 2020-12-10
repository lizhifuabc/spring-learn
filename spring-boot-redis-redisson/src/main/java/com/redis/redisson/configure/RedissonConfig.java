package com.redis.redisson.configure;

import com.redis.redisson.properties.RedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Redisson配置
 *
 * @author lizhifu
 * @date 2020/12/10
 */
@Configuration
@Slf4j
public class RedissonConfig {
    @Resource
    private RedisProperties redisProperties;
    @Bean
    public RedissonClient redissonClient() {
        RedissonClient redissonClient;
        Config config = new Config();
        String url = "redis://" + redisProperties.getHost() + ":" + redisProperties.getPort();
        config.useSingleServer().setAddress(url)
                //reids没有设置密码，此时不能设置
//                .setPassword(redisProperties.getPassword())
                .setDatabase(redisProperties.getDatabase());
        try {
            redissonClient = Redisson.create(config);
            return redissonClient;
        } catch (Exception e) {
            log.error("RedissonClient init redis url:[{}], Exception:", url, e);
            return null;
        }
    }
}
