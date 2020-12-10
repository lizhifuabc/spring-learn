package com.boot.zk.config;

import com.boot.zk.properties.ZkProperties;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Zookeeper配置类
 *
 * @author lizhifu
 * @date 2020/12/10
 */
@Configuration
public class ZkConfg {
    @Resource
    private ZkProperties zkProperties;
    @Bean
    public CuratorFramework curatorFramework() {
        // 第一次重试等待1s
        // 第二次重试等待2s
        // 第三次重试等待4s
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(zkProperties.getBaseSleepTimeMs(), zkProperties.getMaxRetries());
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkProperties.getServer())
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(zkProperties.getConnectionTimeoutMs())
                .sessionTimeoutMs(zkProperties.getSessionTimeoutMs())
                .build();
        client.start();
        return client;
    }
}
