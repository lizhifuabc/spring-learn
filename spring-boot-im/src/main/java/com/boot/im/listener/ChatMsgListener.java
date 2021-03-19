package com.boot.im.listener;

import com.boot.im.domain.MsgDataEvent;
import com.boot.im.type.MsgActionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 监听消息
 * @author lizhifu
 */
@Component
@Slf4j
public class ChatMsgListener {

    @EventListener
    public void getData(MsgDataEvent dataContentEvent) {
        MsgActionEnum action = dataContentEvent.getAction();
        switch (action) {
            case CHAT:
                log.info("聊天消息:{}",dataContentEvent);
                break;
            case RECEIVE:
                log.info("消息签收:{}",dataContentEvent);
                break;
        }

    }
}
