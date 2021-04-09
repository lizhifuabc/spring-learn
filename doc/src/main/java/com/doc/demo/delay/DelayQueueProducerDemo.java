package com.doc.demo.delay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.DelayQueue;

/**
 * 生产者demo
 * @author lizhifu
 * @date 2021/4/9
 */
@Slf4j
@Data
@AllArgsConstructor
public class DelayQueueProducerDemo implements Runnable {
    private DelayQueue<DelayDemo> delayQueue;
    private Integer count;
    private long delayedTime;
    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            try {
                DelayDemo delayDemo = new DelayDemo(
                        new Random().nextInt(1000)+"", delayedTime);
                log.info("put delayDemo {}",delayDemo);
                delayQueue.put(delayDemo);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                log.error(e.getMessage(),e);
            }
        }
    }
}
