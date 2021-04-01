package com.boot.task.config;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 定时任务配置
 *
 * @author lizhifu
 * @date 2021/4/1
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = {"com.boot.task.timer"})
public class TimerConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    /**
     * {@code spring.task.scheduling.pool.size=20} - 最大线程数量
     * {@code spring.task.scheduling.thread-name-prefix=timer-thread- } - 新创建的线程池前缀
     * {@link org.springframework.boot.autoconfigure.task.TaskSchedulingProperties}
     */
    @Bean
    public Executor taskExecutor() {
        return new ScheduledThreadPoolExecutor(20, new BasicThreadFactory.Builder().namingPattern("timer-thread-%d").build());
    }
}
