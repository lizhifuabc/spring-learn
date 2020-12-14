package com.boot.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 *
 * @author lizhifu
 * @date 2020/12/14
 */
@RestController
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "hello";
    }
}
