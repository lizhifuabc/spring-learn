---
title: SpringBoot定时任务
date: 2021-04-01 11:19:08
tags: SpringBoot
categories: SpringBoot

---

1. 定时任务
2. 指定线程池

配置线程池，使用不同线程执行任务，提升执行效率

<!--more-->

# 源码地址

https://github.com/lizhifuabc/spring-learn/tree/main/spring-boot-task

# 配置线程池

### 配置文件配置

```properties
# 最大线程数量
spring.task.scheduling.pool.size=20
# 新创建的线程池前缀
spring.task.scheduling.thread-name-prefix=timer-thread-
```

### 代码配置

```java
/**
 * 定时任务配置
 *
 * @author lizhifu
 * @date 2021/4/1
 */
@Configuration
@EnableScheduling
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
```

# Demo例子

```java
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
```

