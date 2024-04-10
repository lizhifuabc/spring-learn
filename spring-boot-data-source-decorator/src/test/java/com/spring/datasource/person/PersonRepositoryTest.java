package com.spring.datasource.person;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * PersonRepository
 *
 * @author lizhifu
 * @since 2024/4/10
 */
@SpringBootTest
public class PersonRepositoryTest {
    @Resource
    private PersonRepository personRepository;

    @Test
    public void test(){
        Person person = new Person();
        person.setName("lizhifu");
        person.setAge(18);
        personRepository.save(person);

        personRepository.findAll().forEach(System.out::println);
    }
}
