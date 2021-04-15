package com.boot.shutdown.service;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * DemoService
 *
 * @author lizhifu
 * @date 2021/4/15
 */
@Component
public class DemoService {
    @PreDestroy
    public void preDestroy() {
        System.out.println("DemoService is destroyed");
    }
}
