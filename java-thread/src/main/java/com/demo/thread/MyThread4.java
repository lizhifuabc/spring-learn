package com.demo.thread;

/**
 * MyThread4
 *
 * @author lizhifu
 * @date 2021/4/23
 */
public class MyThread4 {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("MyThread4");
            }
        });
        // 启动线程
        thread.start();
    }
}
