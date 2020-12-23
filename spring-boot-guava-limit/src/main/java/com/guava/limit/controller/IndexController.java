package com.guava.limit.controller;

import com.guava.limit.annotation.GuavaLimit;
import com.guava.limit.domain.Index;
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
    @GuavaLimit(qps = 1,timeout = 100)
    public String index(Index param){
        return param.toString();
    }
}
