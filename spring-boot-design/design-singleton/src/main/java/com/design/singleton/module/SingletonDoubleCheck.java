package com.design.singleton.module;

/**
 * 单例设计模式之双重锁校验
 *
 * @author lizhifu
 * @date 2020/12/28
 */
public class SingletonDoubleCheck {
    /**
     * 2.声明本类类型的引用指向本类类型的对象并使用private static关键字共同修饰
     *   程序启动时创建实例，借助static关键字的特殊性
     *   使用volatile关键字可以防止指令重排
     */
    private static volatile SingletonDoubleCheck instance;

    /**
     * 1.私有化构造方法，使用private关键字修饰，此时不允许外部直接创建实例
     */
    private SingletonDoubleCheck(){}

    /**
     * 3.提供公有的get方法负责创建对象并返回出去，使用public static关键字共同修饰
     *   通过条件判断既保障多线程的安全性，又使得效率更高
     * @return
     */
    public static SingletonDoubleCheck getInstance() {
        // 若实例不存在才需要加锁，是对锁的一种优化
        if(null == instance){
            synchronized (SingletonDoubleCheck.class) {
                if (null == instance) {
                    // 非原子操作的流程如下：
                    //   (1) memory = allocate();  分配对象的内存空间
                    //   (2) initInstance(memory); 初始化对象
                    //   (3)instance = memory;    设置instance指向刚分配的内存地址
                    // jvm指令重排后：1-->3-->2，为避免指令重排，使用volatile关键字
                    instance = new SingletonDoubleCheck();
                }
            }
        }
        // 若实例存在则直接返回，不需要加锁
        return instance;
    }
}
