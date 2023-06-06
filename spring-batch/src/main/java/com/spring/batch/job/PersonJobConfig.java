package com.spring.batch.job;

import com.spring.batch.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * person job
 *
 * @author lizhifu
 * @since 2023/6/6
 */
@Configuration
@Slf4j
public class PersonJobConfig {

    /**
     * Job是Spring Batch中的最高级别的概念，
     * 它代表了一个完整的批量处理任务。一个Job由多个Step组成，每个Step代表了一个具体的处理步骤。
     * @param jobRepository jobRepository
     * @param listener listener
     * @param step1 step1
     * @return Job
     */
    @Bean
    public Job personJob(JobRepository jobRepository,
                             JobCompletionNotificationListener listener, Step step1) {
        return new JobBuilder("personJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }
    /**
     * Step是对Job中每个独立任务的封装，
     * Step是Spring Batch中的一个处理步骤，
     * 它包含了一个ItemReader、
     * 一个ItemProcessor和一个ItemWriter。
     * ItemReader用于读取数据，ItemProcessor用于处理数据，ItemWriter用于写入数据。
     * @param jobRepository jobRepository
     * @param transactionManager transactionManager
     * @param writer writer
     * @param processor processor
     * @return Step
     */
    @Bean
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager, PersonItemWriter writer, PersonItemProcessor processor, FlatFileItemReader<Person> reader) {
        return new StepBuilder("step1", jobRepository)
                // 设置每次读取和处理的数据量为10
                .<Person, Person> chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
