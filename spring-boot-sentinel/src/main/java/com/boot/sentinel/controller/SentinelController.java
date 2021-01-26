package com.boot.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * sentinel
 *
 * @author lizhifu
 * @date 2021/1/22
 */
@RestController
public class SentinelController {
    @SentinelResource("HelloWorld")
    @GetMapping("/sentinel")
    public String sentinel(){
        return "sentinel";
    }
}
