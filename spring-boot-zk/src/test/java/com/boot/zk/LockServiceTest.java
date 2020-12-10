package com.boot.zk;

import com.boot.zk.domain.ZkLock;
import com.boot.zk.lock.LockResult;
import com.boot.zk.lock.LockService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * LockService 测试
 *
 * @author lizhifu
 * @date 2020/12/10
 */
@SpringBootTest
public class LockServiceTest {
    @Resource
    LockService lockService;
    @Test
    public void test() throws Exception {
        ZkLock zkLock = new ZkLock();
        zkLock.setLockPath("/lock");
        zkLock.setTimeout(10 * 1000);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println("尝试获取锁。。。"+Thread.currentThread().getName());
                try {
                    LockResult result = lockService.lock(zkLock);
                    if(result.isLock()){
                        System.out.println("获得锁"+Thread.currentThread().getName()+result);
                        Thread.sleep(5000);
                        System.out.println("释放锁"+Thread.currentThread().getName());
                        result.releaseLock();
                    }else{
                        System.out.println("获得锁失败"+Thread.currentThread().getName()+result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(2000 * 60 * 10);
    }
}
