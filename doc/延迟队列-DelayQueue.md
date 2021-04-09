---
title: 延迟队列-DelayQueue
date: 2021-04-09 09:58:54
tags: 
	- 线程
	- 队列
	- Java
categories: Java
---

# 概述

- 指定时间才能获取队列元素
- 队列头元素是最接近过期的元素
- 没有过期元素的话，poll()方法返回null值
- 通过getDelay(TimeUnit.NANOSECONDS)方法的返回值小于等于0来判断
- 不能存放空元素

<!--more-->

# 示例

Delayed对象：

```java
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
```

生产者：每隔0.5秒创建一个新的DelayDemo对象

```java
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
    private Integer messageCount;
    private long delayedTime;
    @Override
    public void run() {
        for (int i = 0; i < messageCount; i++) {
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
```

消费者：

```java
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
```

测试类：

```java
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
```

运行结果：

```java
10:29:10.631 [pool-1-thread-1] INFO com.doc.demo.delay.DelayQueueProducerDemo - put delayDemo DelayDemo(orderNo=94, time=1617935351129)
10:29:11.132 [pool-1-thread-2] INFO com.doc.demo.delay.DelayQueueConsumeDemo - delayQueue take DelayDemo(orderNo=94, time=1617935351129)
10:29:11.136 [pool-1-thread-1] INFO com.doc.demo.delay.DelayQueueProducerDemo - put delayDemo DelayDemo(orderNo=585, time=1617935351636)
10:29:11.640 [pool-1-thread-2] INFO com.doc.demo.delay.DelayQueueConsumeDemo - delayQueue take DelayDemo(orderNo=585, time=1617935351636)
10:29:11.640 [pool-1-thread-1] INFO com.doc.demo.delay.DelayQueueProducerDemo - put delayDemo DelayDemo(orderNo=196, time=1617935352140)
10:29:12.144 [pool-1-thread-2] INFO com.doc.demo.delay.DelayQueueConsumeDemo - delayQueue take DelayDemo(orderNo=196, time=1617935352140)
10:29:12.144 [pool-1-thread-1] INFO com.doc.demo.delay.DelayQueueProducerDemo - put delayDemo DelayDemo(orderNo=372, time=1617935352644)
10:29:12.647 [pool-1-thread-2] INFO com.doc.demo.delay.DelayQueueConsumeDemo - delayQueue take DelayDemo(orderNo=372, time=1617935352644)
10:29:12.648 [pool-1-thread-1] INFO com.doc.demo.delay.DelayQueueProducerDemo - put delayDemo DelayDemo(orderNo=579, time=1617935353148)
10:29:13.150 [pool-1-thread-2] INFO com.doc.demo.delay.DelayQueueConsumeDemo - delayQueue take DelayDemo(orderNo=579, time=1617935353148)
```

