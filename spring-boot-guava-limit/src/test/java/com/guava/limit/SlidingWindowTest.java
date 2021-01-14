package com.guava.limit;

import com.guava.limit.jdk.SlidingWindow;

/**
 * SlidingWindow
 *
 * @author lizhifu
 * @date 2021/1/14
 */
public class SlidingWindowTest {
    public static void main(String[] args) throws InterruptedException {
        SlidingWindow slidingWindow = new SlidingWindow(10,1);
        for (int i = 0; i < 20; i++) {
            slidingWindow.tryAcquire();
            Thread.sleep(500L);
        }
    }
}
