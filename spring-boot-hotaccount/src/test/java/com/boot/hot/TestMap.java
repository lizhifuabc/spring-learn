package com.boot.hot;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ConcurrentHashMap 测试
 *
 * @author lizhifu
 * @date 2020/12/21
 */
public class TestMap {
    public static void main(String[] args) throws Exception {
        ConcurrentHashMap<String, User> map = new ConcurrentHashMap();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    User user = new User();
                    user.getQueue().add(UUID.randomUUID().toString());
                    //putIfAbsent 如果传入key对应的value已经存在，
                    // 就返回存在的value，不进行替换。如果不存在，就添加key和value，返回null
                    User uu = map.putIfAbsent("key", user);
                    if(null != uu){
                        uu.getQueue().add(UUID.randomUUID().toString());
                    }
                }
            });
        }
        Thread.sleep(3000); //模拟等待执行结束
        System.out.println("------" + map.get("key").getQueue().size() + "------");
        executorService.shutdown();
    }
}
