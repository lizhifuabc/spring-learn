package com.boot.im.handler;

import com.boot.im.properties.NettyProperties;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import javax.annotation.Resource;

/**
 * 通道
 * @author lizhifu
 */
public class WSServerInitialzer extends ChannelInitializer<SocketChannel> {

    @Resource
    private ChatHandler chatHandler;

    @Resource
    private HeartBeatHandler heartBeatHandler;

    @Resource
    private NettyProperties nettyProperties;

    @Override
    protected void initChannel(SocketChannel channel) {
        //获取管道（pipeline）
        ChannelPipeline pipeline = channel.pipeline();
        // websocket 基于http协议，所以要有http编解码器
        pipeline.addLast(new HttpServerCodec());
        //在http上有一些数据流产生，有大有小，我们对其进行处理，既然如此，我们需要使用netty 对下数据流写 提供支持，这个类叫：ChunkedWriteHandler
        pipeline.addLast(new ChunkedWriteHandler());
        //对httpMessage 进行聚合处理，聚合成request或 response
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));

        //===========================增加心跳支持==============================

        /**
         * 针对客户端，如果在1分钟时间内没有向服务端发送读写心跳（ALL），则主动断开连接
         * 如果有读空闲和写空闲，则不做任何处理
         */
        pipeline.addLast(new IdleStateHandler(8,10,12));
        //自定义的空闲状态检测的handler
        pipeline.addLast(heartBeatHandler);

        /**
         * 本handler 会帮你处理一些繁重复杂的事情
         * 会帮你处理握手动作：handshaking（close、ping、pong） ping+pong = 心跳
         * 对于websocket 来讲frams 进行传输的，不同的数据类型对应的frams 也不同
         */
        String path = nettyProperties.getPath();
        pipeline.addLast(new WebSocketServerProtocolHandler(path));

        //自定义的handler
        pipeline.addLast(chatHandler);
    }
}
