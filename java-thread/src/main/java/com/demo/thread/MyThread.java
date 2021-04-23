package com.demo.thread;

/**
 * MyThread
 *
 * @author lizhifu
 * @date 2021/4/23
 */
public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("MyThread");
    }
    public static void main(String[] args) {
        // 创建线程
        Thread thread = new MyThread();
        // 启动线程
        thread.start();
    }
}
