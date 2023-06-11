package com.spring.boot.log4j2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author lizhifu
 * @since 2023/6/11
 */
@Slf4j
@RestController
public class HelloController {
    @GetMapping("/")
    public String hello() {
        log.trace("trace 日志.....");
        log.debug("debug 日志.....");
        log.info("info 日志..... ");
        log.warn("warn 日志...");
        log.error("error 日志...");
        return "hello";
    }
}
