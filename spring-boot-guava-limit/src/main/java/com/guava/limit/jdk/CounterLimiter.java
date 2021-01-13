package com.guava.limit.jdk;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 计算器方式实现限流
 *
 * @author lizhifu
 * @date 2021/1/13
 */
public class CounterLimiter {
    /**
     * 初始时间
     */
    private static long start = System.currentTimeMillis();
    /**
     * 请求计数
     */
    private static AtomicInteger count = new AtomicInteger(0);
    /**
     * 限流数
     */
    private static int limit = 10;
    /**
     * 时间窗口限制
     */
    private static final int interval = 10000;
    /**
     * 获取限流锁
     * @return
     */
    public static boolean tryAcquire(){
        long now = System.currentTimeMillis();
        if(now < start + interval){
            //判断是否超过最大请求
            if (count.get() < limit) {
                count.incrementAndGet();
                return true;
            }
            return false;
        }else{
            //超时重置
            count = new AtomicInteger(0);;
            start = now;
            return true;
        }
    }
}
