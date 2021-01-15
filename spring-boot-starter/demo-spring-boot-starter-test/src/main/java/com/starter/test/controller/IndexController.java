package com.starter.test.controller;

import com.demo.starter.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * index
 *
 * @author lizhifu
 * @date 2021/1/15
 */
@RestController
public class IndexController {
    @Resource
    private DemoService demoService;
    @GetMapping("/")
    public String demo() {
        return demoService.say();
    }
}
