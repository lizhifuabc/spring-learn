package com.design.singleton.module;

/**
 * 枚举单例(线程安全)
 *
 * @author lizhifu
 * @date 2020/12/28
 */
public enum SingletonEnum {
    INSTANCE;
    public void getMsg(){

    }
    public static void main(String[] args) {
        SingletonEnum.INSTANCE.getMsg();
    }
}
