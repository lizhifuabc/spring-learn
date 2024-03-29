package com.boot.im.config;

import com.boot.im.channel.ServerChannelInitializer;
import com.boot.im.handler.ChatHandler;
import com.boot.im.handler.HeartBeatHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import jakarta.annotation.Resource;


/**
 * Netty配置
 * @author lizhifu
 */
@Configuration
@Slf4j
@ConditionalOnClass({ServerBootstrap.class})
public class NettyConfig {
    /**
     * 一个主线程组
     * 用于接收客户端的链接，但不做任何处理
     */
    private EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    /**
     * N个工作线程组
     * 从线程组，主线程组会把任务转给从线程组进行处理
     */
    private EventLoopGroup workGroup = new NioEventLoopGroup(512);

    @Resource
    private ServerChannelInitializer serverChannelInitializer;

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
    public ServerBootstrap server() {
        log.info("start  socket server");
        // Server 服务启动
        ServerBootstrap bootstrap = new ServerBootstrap();
        //设置主从线程组
        bootstrap.group(bossGroup, workGroup)
                // 设置nio的双向通道
                .channel(NioServerSocketChannel.class)
                //设置chanel初始化器
                //每一个chanel由多个handler共同组成管道(pipeline)
                .childHandler(serverChannelInitializer);
        return bootstrap;

    }
    /**
     * 关闭netty服务
     */
    @PreDestroy
    public void destroy() {
        bossGroup.shutdownGracefully().syncUninterruptibly();
        workGroup.shutdownGracefully().syncUninterruptibly();
        log.info("关闭 Netty 成功");
    }
}
