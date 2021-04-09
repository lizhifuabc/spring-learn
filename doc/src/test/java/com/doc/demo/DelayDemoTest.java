package com.doc.demo;

import com.doc.demo.delay.DelayDemo;
import com.doc.demo.delay.DelayQueueConsumeDemo;
import com.doc.demo.delay.DelayQueueProducerDemo;
import org.junit.jupiter.api.Test;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * DelayDemoTest
 *
 * @author lizhifu
 * @date 2021/4/9
 */
public class DelayDemoTest {
    @Test
    public void useDelayedQueue() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        DelayQueue<DelayDemo> queue = new DelayQueue<>();
        long delayTime = 500;
        DelayQueueConsumeDemo consumer = new DelayQueueConsumeDemo(
                queue, 5);
        DelayQueueProducerDemo producer = new DelayQueueProducerDemo(
                queue, 5, delayTime);

        executor.submit(producer);
        executor.submit(consumer);

        executor.awaitTermination(5, TimeUnit.SECONDS);
        executor.shutdown();
    }
}
