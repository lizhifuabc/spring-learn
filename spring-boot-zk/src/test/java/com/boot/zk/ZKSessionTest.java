package com.boot.zk;

import com.boot.zk.lock.ZKSession;
import com.boot.zk.properties.ZkProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * ZKSession
 *
 * @author lizhifu
 * @date 2020/12/25
 */
@SpringBootTest
public class ZKSessionTest {
    @Resource
    ZkProperties zkProperties;
    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                ZKSession zkSession = new ZKSession(zkProperties);
                boolean flag = zkSession.lock("path",System.currentTimeMillis());
                System.out.println("加锁结果"+flag);
                zkSession.unlock("path");
            }).start();
        }
        Thread.sleep(2000 * 60 * 10);
    }
}
