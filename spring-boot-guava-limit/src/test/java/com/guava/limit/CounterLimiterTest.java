package com.guava.limit;

import com.guava.limit.jdk.CounterLimiter;

/**
 * CounterLimiter
 *
 * @author lizhifu
 * @date 2021/1/13
 */
public class CounterLimiterTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(CounterLimiter.tryAcquire());
        }
    }
}
