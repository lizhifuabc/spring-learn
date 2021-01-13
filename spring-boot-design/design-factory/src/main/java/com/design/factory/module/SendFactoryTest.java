package com.design.factory.module;

/**
 * 工厂方法模式之工厂类的测试
 *
 * @author Marz
 * @date 2021/1/13
 */
public class SendFactoryTest {

    public static void main(String[] args) {
        // 缺点：代码复杂，可读性略差
        // 优点：扩展性和可维护性更强！  尤其是在创建大量对象的前提下
        // 1.声明工厂类类型的引用指向工厂类类型的对象
        //SendFactory sf = new SendFactory();
        // 2.调用生产方法来实现对象的创建
        //Sender sender = sf.produce("mail");
        //Sender sender = sf.produce("maill");
        //Sender sender = sf.produceMail();
        Sender sender = SendFactory.produceMail();
        // 3.使用对象调用方法模拟发生的行为
        sender.send();

        System.out.println("-------------------------------------");
        // 优点：代码简单，可读性强    在创建单个对象时有优势
        // 缺点：扩展性和可维护性略差
        Sender sender1 = new MailSender();
        sender1.send();
    }
}
