package com.boot.im.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * netty配置
 *
 * @author lizhifu
 * @date 2021/3/19
 */
@ConfigurationProperties(prefix = "netty")
@Component
@Data
public class NettyProperties {
    /**
     * 端口
     */
    private Integer port;
    /**
     * 路径
     */
    private String path;
}
