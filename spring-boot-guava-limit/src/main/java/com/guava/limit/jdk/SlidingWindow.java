package com.guava.limit.jdk;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 滑动窗口：原理演示
 *
 * @author lizhifu
 */
public class SlidingWindow {
    /**
     * 循环队列，就是装多个窗口用
     */
    private AtomicInteger[] timeSlices;
    /**
     * 循环队列的总长度:windowSize * 2 + 1
     */
    private int timeSliceSize;
    /**
     * 每个时间片的时长，以毫秒为单位
     */
    private int timeMillisPerSlice;
    /**
     * 共有多少个时间片（即窗口长度）
     */
    private int windowSize;
    /**
     * 在一个完整窗口期内允许通过的最大阈值
     */
    private int threshold;
    /**
     * 该滑窗的起始创建时间，也就是第一个数据
     */
    private long beginTimestamp;
    /**
     * 最后一个数据的时间戳
     */
    private long lastAddTimestamp;

    public static void main(String[] args) {
        //1秒一个时间片，窗口共5个
        SlidingWindow window = new SlidingWindow(100, 4, 8);
        for (int i = 0; i < 100; i++) {
            System.out.println(window.addCount(2));

            window.print();
            System.out.println("--------------------------");
            try {
                Thread.sleep(102);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public SlidingWindow(int duration, int threshold) {
        //超过10分钟的按10分钟
        if (duration > 600) {
            duration = 600;
        }
        //要求5秒内探测出来的，
        if (duration <= 5) {
            this.windowSize = 5;
            this.timeMillisPerSlice = duration * 200;
        } else {
            this.windowSize = 10;
            this.timeMillisPerSlice = duration * 100;
        }
        this.threshold = threshold;
        // 保证存储在至少两个window
        this.timeSliceSize = windowSize * 2 + 1;

        reset();
    }

    public SlidingWindow(int timeMillisPerSlice, int windowSize, int threshold) {
        this.timeMillisPerSlice = timeMillisPerSlice;
        this.windowSize = windowSize;
        this.threshold = threshold;
        // 保证存储在至少两个window
        // 因为我们在存放数据时采用的是取模的方式确定数组的下标位置
        this.timeSliceSize = windowSize * 2 + 1;

        reset();
    }

    /**
     * 初始化
     */
    private void reset() {
        beginTimestamp = System.currentTimeMillis();
        //窗口个数
        AtomicInteger[] localTimeSlices = new AtomicInteger[timeSliceSize];
        for (int i = 0; i < timeSliceSize; i++) {
            localTimeSlices[i] = new AtomicInteger(0);
        }
        timeSlices = localTimeSlices;
    }

    private void print() {
        for (AtomicInteger integer : timeSlices) {
            System.out.print(integer + "-");
        }
    }

    /**
     * 计算当前所在的时间片的位置index
     */
    private int locationIndex() {
        long now = System.currentTimeMillis();
        //如果当前的key已经超出一整个时间片了，那么就直接初始化就行了，不用去计算了
        //当前时间-上一次添加数据时间
        //单个时间片的时长 * 时间片总数
        if (now - lastAddTimestamp > timeMillisPerSlice * windowSize) {
            reset();
        }
        //(当前时间-第一次添加时间) / 单个时间片的时长
        //队列的总长度
        //取余数(膜) = 时间片的位置index
        return (int) (((now - beginTimestamp) / timeMillisPerSlice) % timeSliceSize);
    }

    /**
     * 增加count个数量
     */
    public boolean addCount(int count) {
        //当前自己所在的位置，是哪个小时间窗
        int index = locationIndex();
//        System.out.println("index:" + index);
        //然后清空自己前面windowSize到2*windowSize之间的数据格的数据
        //譬如1秒分4个窗口，那么数组共计8个窗口
        //当前index为5时，就清空6、7、8、1。然后把2、3、4、5的加起来就是该窗口内的总和
        clearFromIndex(index);

        int sum = 0;
        // 在当前时间片里继续+1
        sum += timeSlices[index].addAndGet(count);
        //加上前面几个时间片
        for (int i = 1; i < windowSize; i++) {
            sum += timeSlices[(index - i + timeSliceSize) % timeSliceSize].get();
        }
        System.out.println(sum + "---" + threshold);

        lastAddTimestamp = System.currentTimeMillis();

        return sum >= threshold;
    }

    private void clearFromIndex(int index) {
        for (int i = 1; i <= windowSize; i++) {
            int j = index + i;
            if (j >= windowSize * 2) {
                j -= windowSize * 2;
            }
            timeSlices[j].set(0);
        }
    }

}