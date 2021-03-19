package com.boot.im.service;

import com.boot.im.properties.NettyProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * InitializingBean
 *
 * @author lizhifu
 * @date 2020/12/31
 */
@Component
@Slf4j
public class InitializingBean implements org.springframework.beans.factory.InitializingBean {
    @Resource
    private ServerBootstrap server;
    @Resource
    private NettyProperties properties;
    @Override
    public void afterPropertiesSet() throws Exception {
        //启动服务端，绑定端口，设置启动的方式为同步的，Netty会一直等待，直到该端口启动完毕。
        ChannelFuture future = server.bind(properties.getPort()).sync();
        if (future.isSuccess()) {
            log.info("启动 Netty 成功");
        }
    }
}
