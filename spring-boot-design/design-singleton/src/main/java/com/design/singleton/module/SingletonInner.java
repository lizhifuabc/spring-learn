package com.design.singleton.module;

/**
 * 单例设计模式之静态内部类实现
 *
 * @author lizhifu
 * @date 2020/12/28
 */
public class SingletonInner {
    /**
     * 2.定义私有静态内部类，该内部类的实例与外部类的实例
     *   没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class SingletonJDKHolder{
        // 静态初始化器，由JVM来保证线程安全
        private static SingletonInner instance = new SingletonInner();
    }

    /**
     * 1.私有化构造方法，使用private关键字修饰，此时不允许外部直接创建实例
     */
    private SingletonInner(){}

    /**
     * 3.提供公有的get方法负责将上述对象返回出去，使用public static关键字共同修饰
     *   利用JVM保证多线程并发访问的正确性：推荐使用
     * @return
     */
    public static SingletonInner getInstance(){
        return SingletonJDKHolder.instance;
    }
}
