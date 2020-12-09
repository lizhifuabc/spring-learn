package com.redis.advanced.service;

import com.google.common.base.Preconditions;
import com.google.common.hash.Funnel;
import com.google.common.hash.Hashing;

/**
 * 布隆过滤器
 * 0代表不存在某个数据，1代表存在某个数据,一般不能删除布隆过滤器里的数据
 * 代码来源于{@link com.google.common.hash.BloomFilter}
 * @author lizhifu
 * @date 2020/12/9
 */
public class BloomFilterHelper<T> {
    private int numHashFunctions;
    private int bitSize;
    /**
     * Guava中定义的一个接口，它和PrimitiveSink配套使用，
     * 主要是把任意类型的数据转化成Java基本数据类型（primitive value，如char，byte，int……）
     * ，默认用java.nio.ByteBuffer实现，最终均转化为byte数组
     */
    private Funnel<T> funnel;

    /**
     * 初始化
     * @param funnel
     * @param expectedInsertions 预计插入量
     * @param fpp 可接受的错误率
     */
    public BloomFilterHelper(Funnel<T> funnel, int expectedInsertions, double fpp) {
        Preconditions.checkArgument(funnel != null, "funnel不能为空");
        this.funnel = funnel;
        bitSize = optimalNumOfBits(expectedInsertions, fpp);
        numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, bitSize);
    }

    /**
     * 散列时，此策略使用Hashing.murmur3_128所有128位。
     * 它看起来与MURMUR128_MITZ_32中的实现不同，
     * 因为我们避免了循环中的乘法，并且执行了（更简单）+ = hash2。
     * 我们还通过与Long.MAX_VALUE进行“与”操作而不是翻转位来将索引更改为正数
     * @param value
     * @return
     */
    public int[] murmurHashOffset(T value) {
        int[] offset = new int[numHashFunctions];

        long hash64 = Hashing.murmur3_128().hashObject(value, funnel).asLong();
        int hash1 = (int) hash64;
        int hash2 = (int) (hash64 >>> 32);
        for (int i = 1; i <= numHashFunctions; i++) {
            int nextHash = hash1 + i * hash2;
            if (nextHash < 0) {
                nextHash = ~nextHash;
            }
            offset[i - 1] = nextHash % bitSize;
        }

        return offset;
    }

    /**
     * 拿来用的
     * 计算m（Bloom过滤器的总位），对于指定的预期插入，该m将达到所需的误报概率。
     * n –预期插入（必须为正）
     * p –误报率（必须为0 <p <1）
     */
    private int optimalNumOfBits(long n, double p) {
        if (p == 0) {
            // 设定最小期望长度
            p = Double.MIN_VALUE;
        }
        return (int) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    /**
     * 给定预期的插入次数和布隆过滤器中的位数，计算最佳k（布隆过滤器中插入的每个元素的哈希数）
     * 参数：
     * n –预期插入（必须为正）
     * m –布隆过滤器中的总位数（必须为正）
     */
    private int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }
}