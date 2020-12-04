package com.learn.redis.service.impl;

import com.learn.redis.domain.Person;
import com.learn.redis.service.PersonService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * PersonService实现类
 *
 * @author lizhifu
 * @date 2020/12/4
 */
@Service
public class PersonServiceImpl implements PersonService {

    /**
     * 结果放入缓存
     * @param person
     * @return
     */
    @CachePut(value = "person", key = "#person.id")
    @Override
    public Person put(Person person) {
        System.out.println("开始保存："+person.getId());
        return person;
    }
    @Cacheable(value = "person", key = "#id")
    @Override
    public Person get(Integer id) {
        System.out.println("开始查询："+id);
        return null;
    }
    @CacheEvict(value = "person", key = "#id")
    @Override
    public void remove(Integer id) {

    }
}
