package com.spring.batch.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * 任务监听
 *
 * @author lizhifu
 * @since 2023/6/6
 */
@Component
@Slf4j
public class JobCompletionNotificationListener implements JobExecutionListener {
    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("JOB执行完成,状态：{}",jobExecution.getStatus());
    }
}
