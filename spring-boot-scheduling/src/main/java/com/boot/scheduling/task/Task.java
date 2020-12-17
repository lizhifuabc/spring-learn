package com.boot.scheduling.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 任务
 *
 * @author lizhifu
 * @date 2020/12/17
 */
@Component
public class Task {
    /**
     * 按照标准时间来算，每隔 10s 执行一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void task1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Task ---> task1() 方法执行了：" + sdf.format(new Date()));
    }

    /**
     * 从启动时间开始，上一次执行完毕时间点之后 2 秒再执行
     * 固定间隔时间
     */
    @Scheduled(fixedRate = 2000)
    public void task2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Task ---> task2() 方法执行了：" + sdf.format(new Date()));
    }

    /**
     * 第一次延迟 5 秒后执行，之后每一次都按每 4 秒执行一次
     * 固定等待时间
     */
    @Scheduled(fixedDelay = 4000, initialDelay = 5000)
    public void task3() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Task ---> task3() 方法执行了：" + sdf.format(new Date()));
    }
}
