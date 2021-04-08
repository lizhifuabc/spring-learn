package com.doc.demo.type;

/**
 * 枚举单例模式
 *
 * @author lizhifu
 * @date 2021/4/8
 */
public enum StatusTypeSingleton {
    INSTANCE;
    StatusTypeSingleton(){}
    public static StatusTypeSingleton getInstance() {
        return INSTANCE;
    }
    public void getMsg() {
        System.out.println("单例模式");
    }
}
