package com.demo.thread;

/**
 * MyThread3
 *
 * @author lizhifu
 * @date 2021/4/23
 */
public class MyThread3 implements Runnable {
    @Override
    public void run() {
        System.out.println("MyThread3");
    }
    public static void main(String[] args) {
        MyThread3 myThread3 = new MyThread3();
        Thread thread = new Thread(myThread3);
        // 启动线程
        thread.start();
    }
}
