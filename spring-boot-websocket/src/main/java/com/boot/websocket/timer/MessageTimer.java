package com.boot.websocket.timer;

import com.boot.websocket.service.WsService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 测试消息自动推送
 * 心跳机制
 * Websocket是前后端交互的长连接，前后端也都可能因为一些情况导致连接失效并且相互之间没有了反应。
 * 因此为了保证连接的可持续性和稳定性，WebSocket心跳机制就应运而生。
 * @author lizhifu
 * @since  2021/3/29
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
        int num = 0;
        try {
            wsService.broadcastMsg("每5s发送消息自动推送 "  + LocalDateTime.now());
        } catch (Exception e) {
            log.error("websocket心跳检测结果发生异常",e);
        }finally {
            log.info("websocket心跳检测结果，共【" + num + "】个连接");
        }
    }
}
