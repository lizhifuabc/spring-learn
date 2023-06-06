package com.spring.batch.job;

import com.spring.batch.domain.Person;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * ItemProcessor用于处理数据，
 * 它可以对读取到的数据进行处理，
 * 并将处理后的数据传递给ItemWriter进行写入。
 *
 * @author lizhifu
 * @since 2023/6/6
 */
@Slf4j
@Component
public class PersonItemProcessor implements ItemProcessor<Person, Person> {
    @Override
    public Person process(final Person person) throws Exception {
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();
        final Person transformedPerson = new Person();
        transformedPerson.setFirstName(firstName);
        transformedPerson.setLastName(lastName);
        log.info("数据转换原数据： (" + person + ") 转换后数据 (" + transformedPerson + ")");
        return transformedPerson;
    }
}
