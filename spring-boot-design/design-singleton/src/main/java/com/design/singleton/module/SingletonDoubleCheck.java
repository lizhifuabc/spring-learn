package com.design.singleton.module;

/**
 * 双重锁校验(线程安全)
 *
 * @author lizhifu
 * @date 2020/12/28
 */
public class SingletonDoubleCheck {
    /** 私有化构造函数，不允许外部直接创建 **/
    private SingletonDoubleCheck(){}
    /** volatile防止指令重排**/
    private static volatile SingletonDoubleCheck instance;
    public static SingletonDoubleCheck getInstance(){
        //存在直接返回
        if(null != instance){
            return instance;
        }
        //对于不存在才加锁，是对锁的一种优化
        synchronized (SingletonDoubleCheck.class){
            if (null == instance){
                //非原子操作
                //1.memory = allocate();  分配对象的内存空间
                //2.initInstance(memory); 初始化对象
                //3.instance = memory;    设置instance指向刚分配的内存地址
                //jvm指令重排后：1-->3-->2
                instance = new SingletonDoubleCheck();
            }
        }
        return instance;
    }
}
