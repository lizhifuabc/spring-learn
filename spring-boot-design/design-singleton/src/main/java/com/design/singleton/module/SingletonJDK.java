package com.design.singleton.module;

/**
 * 利用JVM保证多线程并发访问的正确性：推荐使用
 *
 * @author lizhifu
 * @date 2020/12/28
 */
public class SingletonJDK {
    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class SingletonJDKHolder{
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static SingletonJDK instance = new SingletonJDK();
    }
    /** 私有化构造函数，不允许外部直接创建 **/
    private SingletonJDK(){}

    public static SingletonJDK getInstance(){
        return SingletonJDKHolder.instance;
    }
}
