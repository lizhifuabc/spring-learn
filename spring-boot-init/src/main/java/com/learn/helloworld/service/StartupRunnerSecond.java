package com.learn.helloworld.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 程序启动时运行代码
 * 根据order进行启动顺序
 * @Order 注解的执行优先级是按value值从小到大顺序
 * @author lizhifu
 * @date 2020/12/31
 */
@Component
@Order(value=2)
@Slf4j
public class StartupRunnerSecond implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("StartupRunner 启动 @Order(value=2)");
    }
}
