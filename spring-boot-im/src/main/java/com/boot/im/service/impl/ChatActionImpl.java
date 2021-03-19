package com.boot.im.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.boot.im.domain.Msg;
import com.boot.im.domain.MsgData;
import com.boot.im.domain.MsgDataEvent;
import com.boot.im.handler.ChatHandler;
import com.boot.im.handler.UserChanelHandler;
import com.boot.im.service.MsgActionService;
import com.boot.im.type.MsgActionEnum;
import com.boot.im.type.MsgStatusEnum;
import com.boot.im.type.MsgTypeEnum;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

/**
 * 聊天消息
 *
 * @author lizhifu
 * @date 2021/3/19
 */
public class ChatActionImpl implements MsgActionService {
    @Resource
    private ApplicationContext applicationContext;
    @Override
    public void action(MsgData msgData) {
        // 聊天类型的消息
        MsgActionEnum msgActionEnum = MsgActionEnum.getEnum(msgData.getAction());
        Msg chatMsg = msgData.getMsg();
        Long toUserId = chatMsg.getToUserId();
        Long fromUserId = chatMsg.getFromUserId();
        String message = chatMsg.getMsg();
        MsgTypeEnum msgTypeEnum = MsgTypeEnum.getEnum(msgData.getMsg().getType());
        //发送消息
        Channel receiverChannel = UserChanelHandler.get(toUserId);
        if (receiverChannel != null) {
            Channel findChanel = ChatHandler.users.find(receiverChannel.id());
            if (findChanel != null) {
                //用户在线
                receiverChannel.writeAndFlush(
                        new TextWebSocketFrame(
                                JSONObject.toJSONString(msgData)
                        )
                );
            }
            // 用户离线
            MsgDataEvent dataContentEvent = new MsgDataEvent(applicationContext);
            dataContentEvent.setFromUserId(fromUserId);
            dataContentEvent.setToUserId(toUserId);
            dataContentEvent.setType(msgTypeEnum);
            dataContentEvent.setStatus(MsgStatusEnum.UNREAD);
            dataContentEvent.setMessage(message);
            dataContentEvent.setAction(msgActionEnum);
            applicationContext.publishEvent(dataContentEvent);
        }
    }
}
