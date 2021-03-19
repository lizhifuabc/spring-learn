package com.boot.im.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;


/**
 * 用于检测channel 的心跳handler
 * 继承ChannelInboundHandlerAdapter，目的是不需要实现ChannelRead0 这个方法
 * @author lizhifu
 */
@ChannelHandler.Sharable
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            if(event.state()== IdleState.ALL_IDLE){
                Channel channel = ctx.channel();
                //资源释放
                channel.close();
            }
        }
    }
}
