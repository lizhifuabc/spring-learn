package com.redis.advanced;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 布隆过滤器测试
 * <li>funnel：数据类型(一般是调用Funnels工具类中的)
 * <li>expectedInsertions：期望插入的值的个数
 * <li>fpp：误判率(默认值为0.03)
 * fpp越小，需要的内存空间就越大：0.01需要900多万位数，0.03需要700多万位数
 * <li>strategy：哈希算法
 *
 * @author lizhifu
 * @date 2020/12/9
 */
public class BloomFilterTest {
    /**
     * 预计要插入多少数据
     */
    private static int size = 1000000;
    /**
     * 期望的误判率
     */
    private static double fpp = 0.01;
    /**
     * 布隆过滤器
     */
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);

    public static void main(String[] args) {
        // 首先插入10万样本数据
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }
        // 再用另外十万测试数据，测试误判率
        int count = 0;
        for (int i = size; i < size + 100000; i++) {
            if (bloomFilter.mightContain(i)) {
                count++;
                System.out.println(i + "误判了");
            }
        }
        System.out.println("误判总数:" + count);
    }
}
