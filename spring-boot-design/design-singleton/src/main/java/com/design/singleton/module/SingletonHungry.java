package com.design.singleton.module;

/**
 * 单例设计模式之饿汉式
 *
 * @author lizhifu
 * @date 2020/12/28
 */
public class SingletonHungry {
    /**
     * 2.声明本类类型的引用指向本类类型的对象并使用private static关键字共同修饰
     *   程序启动时创建实例，借助static关键字的特殊性
     */
    private static SingletonHungry instance = new SingletonHungry();

    /**
     * 1.私有化构造方法，使用private关键字修饰，此时不允许外部直接创建实例
     */
    private SingletonHungry() {}

    /**
     * 3.提供公有的get方法负责将上述对象返回出去，使用public static关键字共同修饰
     *   保障多线程的安全性
     * @return
     */
    public static SingletonHungry getInstance() {
        return instance;
    }
}
