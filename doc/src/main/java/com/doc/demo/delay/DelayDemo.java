package com.doc.demo.delay;

import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列演示
 *
 * @author lizhifu
 * @date 2021/4/9
 */
@Data
public class DelayDemo implements Delayed {
    /**
     * 编号
     */
    private String orderNo;
    /**
     * 到期时间
     */
    private long time;
    /**
     *
     * @param orderNo 编号
     * @param delayTime 延迟时间
     */
    public DelayDemo(String orderNo, long delayTime){
        this.orderNo = orderNo;
        //time = 当前时间 + delayTime
        this.time = delayTime + System.currentTimeMillis();
    }
    @Override
    public long getDelay(TimeUnit unit) {
        //time>now
        long diffTime = time- System.currentTimeMillis();
        //结果转换成MILLISECONDS
        return unit.convert(diffTime,TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        //compareTo DelayDemo排序
        return (int)(this.time - ((DelayDemo) o).getTime());
    }
}
