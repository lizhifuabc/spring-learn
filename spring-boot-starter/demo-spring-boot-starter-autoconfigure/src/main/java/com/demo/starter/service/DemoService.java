package com.demo.starter.service;

/**
 * DemoService
 *
 * @author lizhifu
 * @date 2021/1/15
 */
public class DemoService {
    public String say;
    public DemoService(String say){
        this.say = say;
    }
    public String say(){
        return this.say;
    }
}
