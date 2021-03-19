package com.boot.im.config;

import com.boot.im.service.MsgActionService;
import com.boot.im.service.impl.ChatActionImpl;
import com.boot.im.service.impl.ConnectActionImpl;
import com.boot.im.service.impl.KeepActionImpl;
import com.boot.im.service.impl.ReceiveActionImpl;
import com.boot.im.type.MsgActionEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lizhifu
 * @description: 消息bean
 */
@Configuration
public class MsgConfig {

    @Bean
    public ChatActionImpl chatAction() {
        return new ChatActionImpl();
    }

    @Bean
    public ConnectActionImpl connectAction() {
        return new ConnectActionImpl();
    }

    @Bean
    public KeepActionImpl keepAction() {
        return new KeepActionImpl();
    }

    @Bean
    public ReceiveActionImpl receiveAction() {
        return new ReceiveActionImpl();
    }

    @Bean
    public Map<Integer, MsgActionService> actionMap(@Qualifier("chatAction") ChatActionImpl chatAction,
                                                    @Qualifier("connectAction") ConnectActionImpl connectAction,
                                                    @Qualifier("keepAction") KeepActionImpl keepAction,
                                                    @Qualifier("receiveAction") ReceiveActionImpl receiveAction){
        Map<Integer, MsgActionService> map = new HashMap<>();
        map.put(MsgActionEnum.CONNECT.TYPE, connectAction);
        map.put(MsgActionEnum.CHAT.TYPE, chatAction);
        map.put(MsgActionEnum.RECEIVE.TYPE, receiveAction);
        map.put(MsgActionEnum.KEEP.TYPE, keepAction);
        return map;
    }
}
