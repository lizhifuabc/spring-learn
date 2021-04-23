package com.demo.thread;

/**
 * MyThread2
 *
 * @author lizhifu
 * @date 2021/4/23
 */
public class MyThread2 {
    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("MyThread2");
            }
        };
        // 启动线程
        t1.start();
    }
}
