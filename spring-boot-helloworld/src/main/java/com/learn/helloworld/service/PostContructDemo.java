package com.learn.helloworld.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @PostConstruct 演示
 *
 * @author lizhifu
 * @date 2020/12/31
 */
@Slf4j
@Component
public class PostContructDemo {
    public PostContructDemo() {
        log.info("PostContructDemo 构造函数");
    }

    @PostConstruct
    public void demo() {
        log.info("PostContructDemo 方法 demo()");
    }
}
