package com.guava.limit.jdk;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 滑动窗口：原理演示
 * @author lizhifu
 */
@Slf4j
public class SlidingWindow {
    /**
     * 窗口：存放每个请求的时间
     * ConcurrentLinkedQueue 是一个基于链接节点的无界线程安全的队列，
     * 按照先进先出原则对元素进行排序。新元素从队列尾部插入，而获取队列元素，则需要从队列头部获取。
     */
    private ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<Long>();
    /**
     * 单个窗口时间：单位秒
     */
    private int seconds;
    /**
     * 最大限流数,例如限制1分钟内100个请求
     */
    private int maxLimit;


    public SlidingWindow(int maxLimit,int seconds){
        this.maxLimit = maxLimit;
        this.seconds = seconds;
    }

    /**
     * 获取限流
     * @return
     */
    public void tryAcquire(){
        clean();
        //开始时间
        long currentTimeMillis = System.currentTimeMillis();
        int size = count();
        if (size > maxLimit) {
            throw new RuntimeException("超过限流大小");
        }
        log.info("队尾添加元素{}",currentTimeMillis);
        queue.offer(currentTimeMillis);

    }
    /**
     * 统计请求请求总数
     * @return
     */
    public int count() {
        Iterator<Long> it = queue.iterator();
        //获取间隔时间:获取有效的时间窗口
        Long ms = System.currentTimeMillis() - seconds * 1000;
        int count = 0;
        while (it.hasNext()) {
            long t = it.next();
            if (t > ms) {
                count++;
            }
        }
        log.info("元素总数为{}",count);
        return count;
    }
    public void clean(){
        //当前时间 - 单个窗口的时间
        //等于是滑动第一个窗口的时间范围
        Long c = System.currentTimeMillis() - seconds * 1000;
        Long tl = null;
        //获取头部元素:时间
        while ((tl = queue.peek()) != null && tl < c) {
            Long e = queue.poll();
            log.info("移除元素{}",e);
        }
    }
}