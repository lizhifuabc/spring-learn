package com.guava.limit.jdk;

/**
 * 滑动窗口：原理演示
 * TODO
 */
public class ArraySlidingWindow {
    private static long[][] arr = { { 0 }, { 0 }, { 0 }, { 0 }, { 0 }, { 0 } };
    /**
     * 最大请求数量
     */
    private static final int max = 100;
    /**
     * 窗口大小
     */
    private static long size = 60;
    /**
     * 窗口开始时间
     */
    private static long time = System.currentTimeMillis();

    public static boolean tryAcquire() {
        //请求时间
        long nowTime = System.currentTimeMillis();
        // 计算坐标
        long result = (nowTime - time) % size;
        int index = 0;
        if (result == 0) {
            index = (int) (nowTime / time);
        } else {
            index = (int) (nowTime / time + 1);
        }
        if (index > arr.length) {
            // 不在窗口内，将滑动窗口平移
            for (int i = 0; i < index - arr.length; i++) {
                // 将数组平移
                for (int j = 0; j < arr.length - 1; j++) {
                    arr[j][0] = arr[j + 1][0];
                }
                // 将起始时间也向前推进一个窗口
                time += 10000;
            }
            // 本次插入的窗口为最后一个窗口
            index = arr.length - 1;
        }
        // 计算窗口总的请求数是否小于阈值
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i][0];
        }
        if (total > max) {
            return false;
        }
        // 获取小窗口目前的请求值
        long count = arr[index - 1][0];
        // 加上本次请求数
        arr[index - 1][0] = count + 1;
        return true;
    }
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(tryAcquire());
        }
    }
}