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
     * 这个方法有啥用呢？？？没看懂
     */
    public void getMsg() {
    }

    /**
     * main方法也不用写了吧？
     * @param args
     */
    public static void main(String[] args) {
        SingletonEnum.INSTANCE.getMsg();
    }
}
