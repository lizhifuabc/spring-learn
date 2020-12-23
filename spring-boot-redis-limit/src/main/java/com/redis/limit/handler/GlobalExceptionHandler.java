package com.redis.limit.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局拦截器
 * @author lizhifu
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Map handler(RuntimeException ex) {
        Map map = new HashMap();
        map.put("msg",ex.getMessage());
        return map;
    }
}
