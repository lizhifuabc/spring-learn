package com.boot.im.config;

import com.boot.im.handler.ChatHandler;
import com.boot.im.handler.HeartBeatHandler;
import com.boot.im.handler.WSServerInitialzer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Netty配置
 * @author lizhifu
 */
@Configuration
@ConditionalOnClass({NioEventLoopGroup.class,ServerBootstrap.class,
        WSServerInitialzer.class})
public class NettyConfig {

    /**
     * 主线程
     * 用于接受客户端的连接，但是不做任何处理
     * @return
     */
    @Bean("mainGroup")
    @ConditionalOnMissingBean
    public NioEventLoopGroup mainGroup() {
        return new NioEventLoopGroup();
    }
    /**
     * 从线程
     * mainGroup会将任务丢给他，让workerGroup去处理
     * @return
     */
    @Bean("workerGroup")
    @ConditionalOnMissingBean
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup();
    }
    @Bean("chanelInit")
    @ConditionalOnMissingBean
    public WSServerInitialzer wsServerInitialzer() {
        return new WSServerInitialzer();
    }
    @Bean("HeartBeatHandler")
    public HeartBeatHandler HeartBeatHandler() {
        return new HeartBeatHandler();
    }
    @Bean("chatHandler")
    public ChatHandler chatHandler() {
        return new ChatHandler();
    }
    @Bean("server")
    @ConditionalOnMissingBean
    public ServerBootstrap server(NioEventLoopGroup mainGroup,NioEventLoopGroup workerGroup,
                                  WSServerInitialzer chanelInit) {
        return new ServerBootstrap()
                //设置主从线程组
                .group(mainGroup, workerGroup)
                // 设置nio的双向通道
                .channel(NioServerSocketChannel.class)
                //子处理器，用于处理workerGroup
                .childHandler(chanelInit);
    }
}
