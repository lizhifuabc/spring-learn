package com.design.singleton;

/**
 * TODO
 *
 * @author Marz
 * @date 2021/7/22
 */
public class SingletonHungry {

    // 2.声明本类类型的引用指向本类类型的对象，并使用private static关键字共同修饰
    private static SingletonHungry singleton = new SingletonHungry();

    // 1.私有化构造方法，使用private关键字修饰
    private SingletonHungry() {}

    // 3.提供公有的get方法负责将对象返回出去，并使用public static关键字共同修饰
    public static SingletonHungry getInstance() {
        return singleton;
    }
}
