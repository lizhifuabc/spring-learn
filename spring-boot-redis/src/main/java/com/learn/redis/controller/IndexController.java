package com.learn.redis.controller;

import com.learn.redis.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * IndexController
 *
 * @author lizhifu
 * @date 2020/12/4
 */
@RestController
public class IndexController {
    @Resource
    private RedisService redisService;
    @GetMapping("/")
    public String helloWorld(){
        redisService.setCacheObject("expiredKey","expiredKey");
        redisService.expire("expiredKey",5);
        return "helloWorld:"+redisService.getCacheObject("expiredKey");
    }
}
