package com.boot.ehcache.service.impl;

import com.boot.ehcache.domain.Hello;
import com.boot.ehcache.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * HelloServiceImpl实现
 *
 * @author lizhifu
 * @date 2020/12/9
 */
@Service
@Slf4j
public class HelloServiceImpl implements HelloService {

    @Override
    @CachePut(value = "hello", key = "#hello.id")
    public Hello insert(Hello hello) {
        System.out.println("缓存增加"+hello.toString());
        return hello;
    }

    @Override
    @Cacheable(value = "hello", key = "#id")
    public Hello select(Long id) {
        System.out.println("没有走缓存");
        return null;
    }

    @Override
    @CacheEvict(value = "hello", key = "#id")
    public void delete(Long id) {
        System.out.println("缓存删除");
    }
}
