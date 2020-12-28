package com.boot.zk.lock;

import com.boot.zk.properties.ZkProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * zk分布式：原理演示
 * 1 尝试创建临时节点，创建成功则获取到临时锁
 * 2 创建失败，注册监听，监听临时锁，此时锁释放，监听获取到，则下一个客户端获取到锁
 *
 * @author lizhifu
 * @date 2020/12/25
 */
@Slf4j
public class ZKSession {
    /** ZooKeeper客户端 */
    private ZooKeeper zookeeper;
    private ZKSession(){}
    /** 用于阻塞等待连接建立完毕*/
    private  CountDownLatch connecteSign = new CountDownLatch(1);
    /** 用于阻塞等待path建立完毕*/
    private  CountDownLatch pathSign;
    public ZKSession(ZkProperties zkProperties) {
        try {
            this.zookeeper = new ZooKeeper(zkProperties.getServer(), zkProperties.getSessionTimeoutMs(), new ZKWatcher());
            try {
                //堵塞等等链接建立完成
                connecteSign.await();
            } catch (InterruptedException e) {
                log.error("建立链接失败{}",e.getMessage());
            }
            log.info("ZKSession 建立完成");
        } catch (Exception e) {
            log.error("建立链接失败{}",e.getMessage());
        }
    }

    /**
     * 获取分布式锁
     * @param lockPath 锁path
     * @param waitTime 超时时间
     * @return
     */
    public boolean lock(String lockPath,Long waitTime){
        String path = "/lock-" + lockPath;
        try {
            // 默认的权限 Ephemeral节点在Zookeeper中是一个临时结点，
            // 这些节点只要创建它的结点session不挂，它就一直存在，当session中止了，节点也就删除了。
            log.info("开始创建临时节点{}",path);
            zookeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            log.info("创建临时节点成功{}",path);
        } catch (Exception e) {
            log.info("创建临时节点失败{}",e.getMessage());
            while (true) {
                try {
                    // 相当于是给node注册一个监听器，去看看这个监听器是否存在
                    Stat stat = zookeeper.exists(path, true);
                    if (stat != null) {
                        pathSign = new CountDownLatch(1);
                        //堵塞
                        log.info("堵塞创建临时节点{}",path);
                        pathSign.await(waitTime, TimeUnit.MILLISECONDS);
                        pathSign = null;
                    }
                    log.info("重新开始创建临时节点{}",path);
                    zookeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                    return true;
                } catch (Exception ee) {
                    log.info("创建临时节点失败{}",ee.getMessage());
                    continue;
                }
            }
        }
        return true;
    }

    /**
     * 释放掉一个分布式锁
     *
     * @param lockPath 锁path
     */
    public void unlock(String lockPath) {
        String path = "/lock-" + lockPath;
        try {
            zookeeper.delete(path, -1);
            log.info("释放掉一个分布式锁path:{}",path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private class ZKWatcher implements Watcher {
        @Override
        public void process(WatchedEvent watchedEvent) {
            log.info("获取到Watcher事件: {}", watchedEvent.getState());
            if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
                connecteSign.countDown();
            }
            //1 创建Watcher，此时已经开始请求获取分布式锁
            //2 分布式锁会创建失败，进入到建立监听器，此时标识(wait())可以释放
            if (pathSign != null) {
                pathSign.countDown();
            }
        }
    }
}
