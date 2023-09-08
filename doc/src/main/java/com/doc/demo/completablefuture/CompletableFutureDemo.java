package com.doc.demo.completablefuture;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFutureDemo
 *
 * @author lizhifu
 * @since 2023/9/7
 */
public class CompletableFutureDemo {
    @Test
    public void one() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = new CompletableFuture();
        new Thread(()->{
            System.out.println("异步任务......");
            completableFuture.complete(Thread.currentThread().getName());
        }).start();
        // 主线程会被阻塞，等待任务执行结束后
        System.out.println("main线程获取执行结果：" + completableFuture.get());
        System.out.println("main线程结束");
    }
    @Test
    public void test() throws Exception {
        // 创建有返回值的异步任务 ::为方法引用的语法
        CompletableFuture<String> supplyCF = CompletableFuture
                .supplyAsync(CompletableFutureDemo::evenNumbersSum);
        // 执行成功的回调
        supplyCF.thenAccept(System.out::println);
        // 执行过程中出现异常的回调
        supplyCF.exceptionally((e)->{
            e.printStackTrace();
            return "异步任务执行过程中出现异常....";
        });
        // 主线程执行打印1234...操作
        // 因为如果不为CompletableFuture指定线程池执行任务的情况下，
        // CompletableFuture默认是使用ForkJoinPool.commonPool()的线程
        // 同时是作为main线程的守护线程进行的，如果main挂了，执行异步任务的线程也会随之终止结束，并不会继续执行异步任务
        for (int i = 1; i <= 10; i++){
            System.out.println("main线程 - 输出："+i);
            Thread.sleep(50);
        }
    }
    // 求和100内的偶数
    private static String evenNumbersSum() {
        int sum = 0;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return Thread.currentThread().getName()+"线程 - 100内偶数之和："+sum;
    }
}
