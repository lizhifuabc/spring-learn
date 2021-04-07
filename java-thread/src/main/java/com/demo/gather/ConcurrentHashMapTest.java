package com.demo.gather;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * ConcurrentHashMap测试
 *
 * @author lizhifu
 * @date 2021/4/7
 */
public class ConcurrentHashMapTest {
    public static ConcurrentHashMap map = new ConcurrentHashMap();
    private static final ThreadPoolExecutor COMMON_POOL =
            new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2, 1024,
                    15L, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(),
                    (ThreadFactory) Thread::new);
    public static void main(String[] args) {
        COMMON_POOL.execute(()->{
            add();
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println("map大小"+map.size());
    }
    public static void add(){
        String uuid = UUID.randomUUID().toString();
        map.put(uuid,uuid);
    }
}
