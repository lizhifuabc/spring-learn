package com.boot.im.service.impl;

import com.boot.im.domain.MsgData;
import com.boot.im.domain.MsgDataEvent;
import com.boot.im.handler.ChatHandler;
import com.boot.im.handler.UserChanelHandler;
import com.boot.im.service.MsgActionService;
import com.boot.im.type.MsgActionEnum;
import io.netty.channel.Channel;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

/**
 * 初始化连接
 *
 * @author lizhifu
 * @date 2021/3/19
 */
public class ConnectActionImpl implements MsgActionService {
    @Resource
    private ApplicationContext applicationContext;
    @Override
    public void action(MsgData msgData) {
        Long fromUserId = msgData.getMsg().getFromUserId();
        MsgActionEnum msgActionEnum = MsgActionEnum.getEnum(msgData.getAction());

        Channel channel =  ChatHandler.ctx.channel();
        UserChanelHandler.put(fromUserId,channel);

        MsgDataEvent dataContentEvent = new MsgDataEvent(applicationContext);
        dataContentEvent.setFromUserId(fromUserId);
        dataContentEvent.setAction(msgActionEnum);
        applicationContext.publishEvent(dataContentEvent);
    }
}
