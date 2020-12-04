package com.learn.redis.service;

import com.learn.redis.domain.Person;

/**
 * 人
 *
 * @author lizhifu
 * @date 2020/12/4
 */
public interface PersonService {
    /**
     * 存储数据
     * @param person
     * @return
     */
    Person put(Person person);

    /**
     * 获取数据
     * @param id
     * @return
     */
    Person get(Integer id);

    /**
     * 删除数据
     * @param id
     */
    void remove(Integer id);
}
