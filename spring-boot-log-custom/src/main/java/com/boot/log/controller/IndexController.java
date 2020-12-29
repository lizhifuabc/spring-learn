package com.boot.log.controller;

import com.boot.log.log.LogEventListener;
import com.boot.log.log.domain.MyLog;
import com.google.common.eventbus.EventBus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


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
    @GetMapping("/")
    public String index(){
        MyLog myLog = new MyLog();
        myLog.setLogId(1);
        myLog.setLogInfo("测试日志");

        EventBus eventBus = new EventBus("Test");
        eventBus.register(logEventListener);
        eventBus.post(myLog);
        return "index";
    }
}
