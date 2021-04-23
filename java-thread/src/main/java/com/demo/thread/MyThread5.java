package com.demo.thread;

/**
 * MyThread5
 *
 * @author lizhifu
 * @date 2021/4/23
 */
public class MyThread5 {
    public static void main(String[] args) {
        Thread t3 = new Thread(() -> {
            System.out.println("MyThread5");
        });
        t3.start();
    }
}
