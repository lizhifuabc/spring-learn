package com.boot.im.service.impl;

import com.boot.im.domain.MsgData;
import com.boot.im.domain.MsgDataEvent;
import com.boot.im.service.MsgActionService;
import com.boot.im.type.MsgActionEnum;
import org.springframework.context.ApplicationContext;

import jakarta.annotation.Resource;

/**
 * 保持心跳
 *
 * @author lizhifu
 * @date 2021/3/19
 */
public class KeepActionImpl implements MsgActionService {
    @Resource
    private ApplicationContext applicationContext;
    @Override
    public void action(MsgData msgData) {
        MsgActionEnum msgActionEnum = MsgActionEnum.getEnum(msgData.getAction());
        MsgDataEvent dataContentEvent = new MsgDataEvent(applicationContext);
        dataContentEvent.setAction(msgActionEnum);
        applicationContext.publishEvent(dataContentEvent);
    }
}
