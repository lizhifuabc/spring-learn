package com.design.singleton.module;

/**
 * 饿汉模式(线程安全)
 *
 * @author lizhifu
 * @date 2020/12/28
 */
public class SingletonHungry {
    /** 程序启动时创建，借助static关键字的特殊性 **/
    private static SingletonHungry instance = new SingletonHungry();
    /** 私有化构造函数，不允许外部直接创建 **/
    private SingletonHungry(){}
    /**
     *
     * @return
     */
    public static SingletonHungry getInstanse(){
        return instance;
    }
}
