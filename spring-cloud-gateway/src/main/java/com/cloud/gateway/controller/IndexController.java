package com.cloud.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * index
 *
 * @author lizhifu
 * @date 2020/12/24
 */
@RestController
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
