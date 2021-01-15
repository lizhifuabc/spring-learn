package com.doc.demo;

/**
 * SynchronizedDemo2
 *
 * @author lizhifu
 * @date 2021/1/15
 */
public class SynchronizedDemo2 {
    static int j = 0;
    public synchronized void increase(){
        j++;
    }
    public void increase2(){
        synchronized(SynchronizedDemo.class){
            j++;
        }
    }
    public static synchronized void increase3(){
        j++;
    }
}
