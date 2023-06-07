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
        return "two"+ LocalDateTime.now();
    }
}
