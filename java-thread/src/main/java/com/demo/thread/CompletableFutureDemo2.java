package com.demo.thread;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

/**
 * CompletableFutureDemo2
 *
 * @author lizhifu
 * @date 2021/4/2
 */
public class CompletableFutureDemo2 {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(CompletableFutureDemo2::doSomeThing);
        // 如果执行成功:
        cf.thenAccept((result) -> {
            System.out.println("doSomeThing: " + result);
        });
        CompletableFuture cf2 = CompletableFuture.runAsync(CompletableFutureDemo2::doSomeThingVoid);
        // 如果执行成功:
        cf2.thenAccept((result) -> {
            System.out.println("doSomeThingVoid: " + result);
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
    public static void doSomeThingVoid() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }
}
