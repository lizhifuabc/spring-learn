package com.boot.task.timer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * demo
 *
 * @author lizhifu
 * @date 2021/4/1
 */
@Component
@Slf4j
public class DemoTimer {
    /**
     * 每隔 10s 执行一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void demo1() {
        log.info("【demo1】开始执行：{}", LocalDateTime.now());
    }

    /**
     * 启动时间开始，间隔 2s 执行
     * 固定间隔时间
     */
    @Scheduled(fixedRate = 2000)
    public void demo2() {
        log.info("【demo2】开始执行：{}", LocalDateTime.now());
    }

    /**
     * 启动时间开始，延迟 5s 后间隔 4s 执行
     * 固定等待时间
     */
    @Scheduled(fixedDelay = 4000, initialDelay = 5000)
    public void demo3() {
        log.info("【demo3】开始执行：{}", LocalDateTime.now());
    }
}
