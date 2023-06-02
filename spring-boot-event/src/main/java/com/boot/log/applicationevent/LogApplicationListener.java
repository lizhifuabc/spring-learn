package com.boot.log.applicationevent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 监听事件
 *
 * @author lizhifu
 * @since 2023/6/2
 */
@Slf4j
@Component
public class LogApplicationListener implements ApplicationListener<LogApplicationEvent> {
    @Override
    public void onApplicationEvent(LogApplicationEvent event) {
        log.info("LogApplicationListener 监听事件:{}",event.getSource().toString());
    }
}
