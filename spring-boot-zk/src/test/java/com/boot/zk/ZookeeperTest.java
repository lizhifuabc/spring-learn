package com.boot.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * ZookeeperTest
 *
 * @author lizhifu
 * @date 2020/12/28
 */
public class ZookeeperTest implements Watcher {
    /** 节点事件信号 */
    private static CountDownLatch pathSemaphore;
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zookeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 3000,new ZookeeperTest());
        Stat stat = zookeeper.exists("/lock",true);
        zookeeper.create("/lock","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zookeeper.delete("/lock",-1);
        pathSemaphore = new CountDownLatch(1);
        pathSemaphore.await(5, TimeUnit.SECONDS);
        System.out.println("await结束");
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("事件状态:" + event.getState() +",事件类型:" +  event.getType() +",事件涉及路径:" + event.getPath());
    }
}
