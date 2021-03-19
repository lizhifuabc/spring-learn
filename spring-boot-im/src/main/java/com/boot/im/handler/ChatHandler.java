package com.boot.im.handler;

import com.alibaba.fastjson.JSONObject;
import com.boot.im.domain.MsgData;
import com.boot.im.service.MsgActionFactory;
import com.boot.im.type.MsgActionEnum;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * 用于处理消息的handler
 * @author lizhifu
 */
@ChannelHandler.Sharable
@Slf4j
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    /**
     * 用于记录和管理所有客户端的channel
     */
    public static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static ChannelHandlerContext ctx;

    @Resource
    private MsgActionFactory msgActionFactory;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        String content = msg.text();
        ChatHandler.ctx = ctx;
        // 获取客户端发来的消息
        MsgData dataContent = JSONObject.parseObject(content,MsgData.class);
        log.info("接收到的消息为{}",dataContent.toString());
        // 业务操作
        MsgActionEnum msgActionEnum = MsgActionEnum.getEnum(dataContent.getAction());
        msgActionFactory.getMsgStrategy(msgActionEnum).action(dataContent);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        users.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        users.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        //发生了异常后关闭连接，同时从channelgroup移除
        ctx.channel().close();
        users.remove(ctx.channel());
    }
}
