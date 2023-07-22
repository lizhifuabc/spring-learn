package com.doc.demo.volatilelearn;

/**
 * volatile:单例
 * 双重检测的单例模式
 * @author lizhifu
 * @since 2023/7/17
 */
public class VolatileSingleton {
    private static VolatileSingleton singleton;

    private VolatileSingleton(){}

    public static VolatileSingleton getInstance(){
        if(singleton == null){
            synchronized(VolatileSingleton.class){
                if(singleton == null){
                    singleton = new VolatileSingleton();
                }
            }
        }
        return singleton;
    }
}
