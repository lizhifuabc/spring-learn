package com.doc.demo.delay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.DelayQueue;

/**
 * 消费者demo
 *
 * @author lizhifu
 * @date 2021/4/9
 */
@Slf4j
@Data
@AllArgsConstructor
public class DelayQueueConsumeDemo implements Runnable {
    private DelayQueue<DelayDemo> delayQueue;
    private Integer count;
    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            try {
                DelayDemo delayDemo = delayQueue.take();
                log.info("delayQueue take {}",delayDemo);
            } catch (InterruptedException e) {
                log.error(e.getMessage(),e);
            }
        }
    }
}
