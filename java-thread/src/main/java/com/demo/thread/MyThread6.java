package com.demo.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * MyThread6
 *
 * @author lizhifu
 * @date 2021/4/23
 */
public class MyThread6 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int num = new Random().nextInt(10);
        System.out.println("MyThread6随机数：" + num);
        return num;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread6 myThread6 = new MyThread6();
        FutureTask<Integer> futureTask = new FutureTask<>(myThread6);
        // 创建线程
        Thread thread = new Thread(futureTask);
        // 启动线程
        thread.start();
        // 得到线程执行的结果
        int result = futureTask.get();
        System.out.println("执行结果：" + result);
    }
}
