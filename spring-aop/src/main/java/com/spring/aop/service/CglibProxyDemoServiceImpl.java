package com.spring.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * CglibProxyDemoServiceImpl 服务
 *
 * @author lizhifu
 * @since 2023/9/17
 */
@Service
@Slf4j
public class CglibProxyDemoServiceImpl {
    public void method1() {
        log.info("CglibProxyDemoServiceImpl.method1()");
    }

    public String method2() {
        log.info("CglibProxyDemoServiceImpl.method2()");
        return "hello world";
    }

    public String method3() throws Exception {
        log.info("CglibProxyDemoServiceImpl.method3()");
        throw new Exception("some exception");
    }
}
