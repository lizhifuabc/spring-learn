package com.redis.advanced.crash;

import com.redis.advanced.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * 缓存雪崩(简单演示):
 * 三方系统
 * <li>用户A</li>
 * <li>缓存B</li>
 * <li>数据库C</li>
 * <li>简单请求流程：A-->B-->C，此时B突然挂掉，那么所有的请求都到了C，那么C压力突增，也有可能会挂掉</li>
 * @author lizhifu
 * @date 2020/12/8
 */
@RestController
@Slf4j
public class CacheCrashController {
    @Resource
    private RedisService redisService;
    @GetMapping("cacheCrash")
    public String cacheCrash(){
        String cacheCrash = "init";
        try{
            //测试的过程我们可以关闭redis模拟崩溃
            cacheCrash = redisService.getCacheObject("cacheCrash");
            if(cacheCrash == null){
                redisService.setCacheObject("cacheCrash","cacheCrash");
            }
        }catch (Exception e){
            log.error("缓存崩溃");
            //缓存崩溃 去数据库查询
            cacheCrash = "mysql:缓存崩溃了";
        }

        return cacheCrash;
    }
}
