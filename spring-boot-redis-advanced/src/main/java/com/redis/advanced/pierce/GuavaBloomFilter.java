package com.redis.advanced.pierce;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * Guava实现布隆过滤器
 * TODO 基本原理
 * @author lizhifu
 * @date 2021/1/14
 */
public class GuavaBloomFilter {
    /**
     * 预计要插入多少数据
     */
    private static int size = 1000000;
    /**
     * 期望的误判率
     */
    private static double fpp = 0.01;

    private static BloomFilter<Integer> bloomFilter =
            BloomFilter.create(Funnels.integerFunnel(), size, fpp);

    public static void main(String[] args) {
        //插入数据
        for (int i = 0; i < 10 ; i++) {
            bloomFilter.put(i);
        }
        for (int i = 10; i < 20 ; i++) {
            System.out.println("========"+bloomFilter.mightContain(i));
        }
    }
}
