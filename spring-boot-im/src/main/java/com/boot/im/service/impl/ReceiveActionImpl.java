package com.boot.im.service.impl;

import com.boot.im.domain.Msg;
import com.boot.im.domain.MsgData;
import com.boot.im.domain.MsgDataEvent;
import com.boot.im.service.MsgActionService;
import com.boot.im.type.MsgActionEnum;
import com.boot.im.type.MsgStatusEnum;
import org.springframework.context.ApplicationContext;

import jakarta.annotation.Resource;

/**
 * 消息接收
 *
 * @author lizhifu
 * @date 2021/3/19
 */
public class ReceiveActionImpl implements MsgActionService {
    @Resource
    private ApplicationContext applicationContext;
    @Override
    public void action(MsgData msgData) {
        MsgActionEnum msgActionEnum = MsgActionEnum.getEnum(msgData.getAction());
        Msg chatMsg = msgData.getMsg();
        Long toUserId = chatMsg.getToUserId();
        Long fromUserId = chatMsg.getFromUserId();
        // 签收消息类型
        MsgDataEvent dataContentEvent = new MsgDataEvent(applicationContext);
        dataContentEvent.setStatus(MsgStatusEnum.READ);
        dataContentEvent.setFromUserId(fromUserId);
        dataContentEvent.setToUserId(toUserId);
        dataContentEvent.setAction(msgActionEnum);
        applicationContext.publishEvent(dataContentEvent);
    }
}
