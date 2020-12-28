package com.boot.zk;

import com.boot.zk.lock.ZKSession;
import com.boot.zk.lock.ZooKeeperLock;
import com.boot.zk.properties.ZkProperties;
import org.apache.zookeeper.KeeperException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * ZooKeeperLockTest
 *
 * @author lizhifu
 * @date 2020/12/28
 */
@SpringBootTest
public class ZooKeeperLockTest {
    @Resource
    ZkProperties zkProperties;
    @Test
    public void test() throws Exception {
        ZooKeeperLock zkSession = new ZooKeeperLock(zkProperties,"path",(1000*2L));
        zkSession.getZookeeper().exists("/lock/path",true);
//        for (int i = 0; i < 2; i++) {
//            new Thread(() -> {
//                ZooKeeperLock zkSession = new ZooKeeperLock(zkProperties,"path",(1000*2L));
//                boolean flag = false;
//                try {
//                    flag = zkSession.lock();
//                } catch (KeeperException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("加锁结果"+flag);
//            }).start();
//        }
        Thread.sleep(2000 * 60 * 10);
    }
}
