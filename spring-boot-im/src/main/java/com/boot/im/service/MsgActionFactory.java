package com.boot.im.service;

import com.boot.im.type.MsgActionEnum;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Map;

/**
 * MsgActionFactory
 * @author lizhifu
 */
@Service
public class MsgActionFactory {

    @Resource
    private Map<String, MsgActionService> actionMap;

    public MsgActionService getMsgStrategy(MsgActionEnum msgActionEnum) {
        MsgActionService msgStrategy = actionMap.get(msgActionEnum.TYPE);
        if (null == msgStrategy) {
            throw new RuntimeException("no MsgActionService");
        }
       return msgStrategy;
    }
}
