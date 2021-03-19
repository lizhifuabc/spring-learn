package com.boot.im.service;

import com.boot.im.domain.MsgData;

/**
 * 消息动作
 *
 * @author lizhifu
 * @date 2021/3/19
 */
public interface MsgActionService {
    /**
     * 消息动作
     * @param msgData
     */
    void action(MsgData msgData);
}
