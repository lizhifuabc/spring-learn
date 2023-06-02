package com.boot.log.applicationevent;

import com.boot.log.domain.MyLog;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 日志事件
 *
 * @author lizhifu
 * @since 2023/6/2
 */
public class LogApplicationEvent extends ApplicationEvent {
    public LogApplicationEvent(MyLog myLog) {
        super(myLog);
    }

    /**
     * 基于秒级定时的事件发布功能
     * @param myLog 日志
     * @param clock 时钟
     */
    public LogApplicationEvent(MyLog myLog, Clock clock) {
        super(myLog, clock);
    }
}
