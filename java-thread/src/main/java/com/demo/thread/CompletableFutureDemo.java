package com.demo.thread;


import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture
 *
 * @author lizhifu
 * @date 2021/4/2
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws InterruptedException {
        // 创建异步执行任务:
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(CompletableFutureDemo::doSomeThing);
        // 如果执行成功:
        cf.thenAccept((result) -> {
            System.out.println("doSomeThing: " + result);
        });
        // 如果执行异常:
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }
    public static String doSomeThing() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return "doSomeThing: "+ LocalDateTime.now();
    }
}
