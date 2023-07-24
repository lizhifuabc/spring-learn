package com.boot.aot.service;

import org.springframework.stereotype.Service;

/**
 * demo
 *
 * @author lizhifu
 * @since 2023/7/24
 */
@Service
public class DemoService {
    public String demo(){
        return "demo" + System.currentTimeMillis();
    }
}
