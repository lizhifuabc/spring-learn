package com.guava.limit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * Guava
 *
 * @author lizhifu
 * @date 2021/1/13
 */
public class LimiterTest {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = RateLimiter.create(100);
        Thread.sleep(100L);
        for(int i=1;i<100;i++){
            if (limiter.tryAcquire(1, TimeUnit.MILLISECONDS)){
                System.out.println("第"+i+"次请求成功");
            }else{
                System.out.println("第"+i+"次请求拒绝");
            }
        }
    }
}
