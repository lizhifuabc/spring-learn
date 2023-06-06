package com.spring.batch.job;

import com.spring.batch.domain.Person;
import com.spring.batch.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * ItemWriter用于写入数据，它可以将处理后的数据写入到文件、数据库、消息队列等数据源中。
 *
 * @author lizhifu
 * @since 2023/6/6
 */
@Slf4j
@Component
public class PersonItemWriter implements ItemWriter<Person> {
    private final PersonRepository personRepository;

    public PersonItemWriter(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void write(Chunk<? extends Person> chunk) throws Exception {
        chunk.getItems().forEach(person -> {
            log.info("写入数据库:{}",person);
            personRepository.save(person);
        });
    }

    /**
     * ItemWriter用于写入数据，它可以将处理后的数据写入到文件、数据库、消息队列等数据源中。
     * @param dataSource 数据源
     * @return JdbcBatchItemWriter
     */
    @Bean
    public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Person>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO person (first_name, last_name) VALUES (:firstName, :lastName)")
                .dataSource(dataSource)
                .build();
    }
}
