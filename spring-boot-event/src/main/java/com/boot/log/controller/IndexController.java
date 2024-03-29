package com.boot.log.controller;

import com.boot.log.applicationevent.LogApplicationEvent;
import com.boot.log.log.LogEventListener;
import com.boot.log.domain.MyLog;
import com.google.common.eventbus.EventBus;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * index
 *
 * @author lizhifu
 * @date 2020/12/29
 */
@RestController
public class IndexController {
    @Resource
    private LogEventListener logEventListener;
    @Autowired
    private ApplicationEventPublisher publisher;
    @GetMapping("/")
    public String index(){
        MyLog myLog = new MyLog();
        myLog.setLogId(1);
        myLog.setLogInfo("测试日志");

        EventBus eventBus = new EventBus("Test");
        eventBus.register(logEventListener);
        eventBus.post(myLog);

        LogApplicationEvent logApplicationEvent = new LogApplicationEvent(myLog);
        publisher.publishEvent(logApplicationEvent);
        return "index";
    }
}
