package com.design.singleton.module;

/**
 * 懒汉模式
 *
 * @author lizhifu
 * @date 2020/12/28
 */
public class Singleton {
    public static Singleton instance;
    /** 私有化构造函数，不允许外部直接创建 **/
    private Singleton(){}

    /**
     * 使用synchronized关键字：保障线程安全性
     * @return
     */
    public static synchronized Singleton getInstanse(){
        if(null == instance){
            instance = new Singleton();
        }
        return instance;
    }
    /**
     * 线程不安全性
     * @return
     */
    public static Singleton getInstanse2(){
        if(null == instance){
            instance = new Singleton();
        }
        return instance;
    }
}
