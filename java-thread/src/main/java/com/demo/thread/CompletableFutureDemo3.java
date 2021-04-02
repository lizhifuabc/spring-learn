package com.demo.thread;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

/**
 * CompletableFutureDemo2
 *
 * @author lizhifu
 * @date 2021/4/2
 */
public class CompletableFutureDemo3 {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(CompletableFutureDemo3::doSomeThing);
        CompletableFuture cf2 = CompletableFuture.runAsync(CompletableFutureDemo3::doSomeThingVoid);
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
