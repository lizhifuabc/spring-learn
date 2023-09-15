package com.spring.cloud.openfeign.two.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * two controller
 *
 * @author lizhifu
 * @since 2023/6/6
 */
@RestController
@RequestMapping("/two")
public class TwoController {
    @GetMapping("/two")
    public String two(){
        // 随机模拟异常
        if(Math.random() > 0.5){
            throw new RuntimeException("two error");
        }
        // 随机模拟超时
        if(Math.random() > 0.5){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "two"+ LocalDateTime.now();
    }
}
