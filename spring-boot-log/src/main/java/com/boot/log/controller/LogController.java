package com.boot.log.controller;

import com.boot.log.annotation.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * log
 *
 * @author lizhifu
 * @date 2020/12/1
 */
@RestController
public class LogController {
    /**
     * log
     * @param msg msg
     */
    @Log(title="log")
    @GetMapping("/log")
    public String log(String msg) {
        return  msg;
    }
}
