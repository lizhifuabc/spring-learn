package com.design.factory.module;

/**
 * 工厂方法模式之工厂类
 *
 * @author Marz
 * @date 2021/1/12
 */
public class SendFactory {

    /**
     * 根据参数类型进行实例创建的工厂方法
     * @param type
     * @return
     */
    public Sender produce(String type) {
        //System.out.println("随便加一句打印进行测试");
        if ("mail".equals(type)) {
            return new MailSender();
        }
        if ("sms".equals(type)) {
            return new SmsSender();
        }
        return null;
    }

    /**
     * 创建邮件发送者的方法
     * @return
     */
    public static Sender produceMail() {
        return new MailSender();
    }

    /**
     * 创建短信发送者的方法
     * @return
     */
    public static Sender produceSms() {
        return new SmsSender();
    }
}
