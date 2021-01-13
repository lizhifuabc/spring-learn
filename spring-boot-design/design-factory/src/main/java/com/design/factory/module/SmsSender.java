package com.design.factory.module;

/**
 * 工厂方法模式之短信发送者
 *
 * @author Marz
 * @date 2021/1/12
 */
public class SmsSender implements Sender{
    /**
     * 模拟发送短信的行为
     */
    @Override
    public void send() {
        System.out.println("正在发送短信...");
    }
}
