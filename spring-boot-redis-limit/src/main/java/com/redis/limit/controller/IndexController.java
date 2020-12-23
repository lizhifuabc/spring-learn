package com.redis.limit.controller;

import com.redis.limit.annotation.RedisLimit;
import com.redis.limit.domain.Index;
import com.redis.limit.enums.LimitKeyType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexController
 *
 * @author lizhifu
 * @date 2020/12/23
 */
@RestController
public class IndexController {
    @GetMapping("/")
    @RedisLimit(max = 5,key = "param",timeout = 1000*2,keyType = LimitKeyType.REQUEST_PARAM)
    @RedisLimit(max = 5,key = "index",timeout = 1000*2)
    public String index(Index param){
        return param.toString();
    }
}
