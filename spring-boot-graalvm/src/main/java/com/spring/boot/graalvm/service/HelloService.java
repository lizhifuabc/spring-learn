package com.spring.boot.graalvm.service;

import org.springframework.stereotype.Service;

/**
 * HelloService
 *
 * @author lizhifu
 * @since 2023/6/2
 */
@Service
public class HelloService {
    public String sayHello(){
        return "hello world";
    }
}
