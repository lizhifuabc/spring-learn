package com.boot.websocket.timer;

import com.boot.websocket.service.WsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 测试消息自动推送
 *
 * @author lizhifu
 * @date 2021/3/29
 */
@Slf4j
@Component
@EnableScheduling
public class MessageTimer {
    @Resource
    private WsService wsService;
    /**
     * 每5s发送
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
        try {
            wsService.broadcastMsg("消息自动推送 "  + LocalDateTime.now().toString());
        } catch (Exception e) {
            log.error("MessageTimer error",e);
        }
    }
}
