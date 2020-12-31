package com.design.singleton.module;

/**
 * 单例设计模式之枚举实现
 *
 * @author lizhifu
 * @date 2020/12/28
 */
public enum SingletonEnum {
    /**
     * 1.定义枚举类型的常量代表本类类型的实例
     */
    INSTANCE;

    /**
     * 演示方法
     */
    public void getMsg() {
    }

    /**
     * main方法
     * @param args
     */
    public static void main(String[] args) {
        SingletonEnum.INSTANCE.getMsg();
    }
}
