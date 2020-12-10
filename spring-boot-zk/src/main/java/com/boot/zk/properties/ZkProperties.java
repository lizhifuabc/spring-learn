package com.boot.zk.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * zk配置文件
 *
 * @author lizhifu
 * @date 2020/12/10
 */
@Data
@Component
@ConfigurationProperties(prefix = "zk")
@PropertySource(value = { "classpath:zk.properties" })
public class ZkProperties {
    private String server;
    private int maxRetries;
    private int baseSleepTimeMs;
    private int connectionTimeoutMs;
    private int sessionTimeoutMs;
}
