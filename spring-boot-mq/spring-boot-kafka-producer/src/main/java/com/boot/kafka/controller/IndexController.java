package com.boot.kafka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * index
 *
 * @author lizhifu
 * @date 2021/1/4
 */
@RestController
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
