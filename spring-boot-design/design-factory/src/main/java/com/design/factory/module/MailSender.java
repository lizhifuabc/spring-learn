package com.design.factory.module;

/**
 * 工厂方法模式之邮件发送者
 *
 * @author Marz
 * @date 2021/1/12
 */
public class MailSender implements Sender {
    /**
     * 模拟发送邮件的行为
     */
    @Override
    public void send() {
        System.out.println("正在发送邮件...");
    }
}
