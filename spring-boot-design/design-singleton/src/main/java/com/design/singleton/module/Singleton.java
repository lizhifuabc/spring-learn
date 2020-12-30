package com.design.singleton.module;

/**
 * 单例设计模式之懒汉式
 *
 * @author lizhifu
 * @date 2020/12/28
 */
public class Singleton {
    /**
     * 2.声明本类类型的引用指向本类类型的对象并使用private static关键字共同修饰
     */
    private static Singleton instance = null;

    /**
     * 1.私有化构造方法，使用private关键字修饰，此时不允许外部直接创建实例
     */
    private Singleton(){}

    /**
     * 3.提供公有的get方法负责创建对象并返回出去，使用public static关键字共同修饰
     * 版本一：不保障多线程的安全性
     * @return
     */
    public static Singleton getInstance(){
        if(null == instance){
            instance = new Singleton();
        }
        return instance;
    }

    /**
     * 3.提供公有的get方法负责创建对象并返回出去，使用public static关键字共同修饰
     *   版本二：保障多线程的安全性
     * @return
     */
    public static synchronized Singleton getInstance2(){
        if(null == instance){
            instance = new Singleton();
        }
        return instance;
    }

    /**
     * 3.提供公有的get方法负责创建对象并返回出去，使用public static关键字共同修饰
     *   版本三：保障多线程的安全性，通过条件判断使得效率更高
     * @return
     */
    public static synchronized Singleton getInstance3(){
        if(null == instance){
            synchronized (Singleton.class) {
                if (null == instance) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
