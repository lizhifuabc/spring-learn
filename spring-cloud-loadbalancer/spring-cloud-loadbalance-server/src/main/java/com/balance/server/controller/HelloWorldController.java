package com.balance.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * HelloWorldController
 *
 * @author lizhifu
 * @date 2021/1/15
 */
@RestController
public class HelloWorldController {
    @GetMapping("/say")
    public String say(){
        Random rand = new Random();
        return "say:" + rand.nextInt();
    }
    @GetMapping("/sayHello")
    public String sayHello(){
        Random rand = new Random();
        return "sayHello:" + rand.nextInt();
    }
}
