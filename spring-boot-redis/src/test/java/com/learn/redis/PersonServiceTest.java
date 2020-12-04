package com.learn.redis;

import com.learn.redis.domain.Person;
import com.learn.redis.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * PersonService测试
 *
 * @author lizhifu
 * @date 2020/12/4
 */
@SpringBootTest
public class PersonServiceTest {
    @Resource
    PersonService personService;
    @Test
    public void test(){
        Person person = new Person();
        person.setId(1);
        person.setName("lizhifu");

        personService.put(person);

        Person daoPerson = personService.get(1);
        System.out.println(daoPerson.toString());
    }
}
