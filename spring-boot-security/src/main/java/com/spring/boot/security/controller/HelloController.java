package com.spring.boot.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取已经登录的用户信息
 *
 * @author lizhifu
 * @since 2023/6/8
 */
@RestController
public class HelloController {
    @GetMapping("/")
    public String hello(Authentication authentication) {
        return "Hello, " + authentication.getName() + "!";
    }
}
