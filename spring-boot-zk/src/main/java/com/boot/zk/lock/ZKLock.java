package com.boot.zk.lock;

import com.boot.zk.properties.ZkProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 1.一把锁，被多个竞争，此时会排队，第一个拿到锁的会执行，然后释放锁；
 * 2.其他线程都会去监听排在自己前面的那个人创建的 node 上
 * 3.一旦锁被释放，ZooKeeper进行通知，此时就可以执行代码了。
 *
 * @author lizhifu
 * @date 2020/12/28
 */
@Slf4j
public class ZKLock{
    private int sessionTimeout = 30000;
    private String productId;

    /** ZooKeeper客户端 */
    private ZooKeeper zookeeper;
    private ZKLock(){}
    /** 用于阻塞等待连接建立完毕*/
    private CountDownLatch connecteSign = new CountDownLatch(1);
    /** 用于阻塞等待path建立完毕*/
    private  CountDownLatch pathSign;
    private String waitNode;
    private String lockNode;
    private String locksRoot = "/lock-";
    public ZKLock(ZkProperties zkProperties) {
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
    public void acquireDistributedLock() {
        try {
            if (this.tryLock()) {
                return;
            } else {
                waitForLock(waitNode, sessionTimeout);
            }
        } catch (KeeperException e) {

        } catch (InterruptedException e) {

        }
    }
    public boolean tryLock() {
        try {
            // 传入进去的locksRoot + “/” + productId
            // 假设productId代表了一个商品id，比如说1
            // locksRoot = locks
            // /locks/10000000000，/locks/10000000001，/locks/10000000002
            lockNode = zookeeper.create(locksRoot + "/" + productId, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            // 看看刚创建的节点是不是最小的节点
            // locks：10000000000，10000000001，10000000002
            List<String> locks = zookeeper.getChildren(locksRoot, false);
            Collections.sort(locks);

            if(lockNode.equals(locksRoot+"/"+ locks.get(0))){
                //如果是最小的节点,则表示取得锁
                return true;
            }

            //如果不是最小的节点，找到比自己小1的节点
            int previousLockIndex = -1;
            for(int i = 0; i < locks.size(); i++) {
                if(lockNode.equals(locksRoot + "/" + locks.get(i))) {
                    previousLockIndex = i - 1;
                    break;
                }
            }

            this.waitNode = locks.get(previousLockIndex);
        } catch (KeeperException e) {

        } catch (InterruptedException e) {
        }
        return false;
    }
    private boolean waitForLock(String waitNode, long waitTime) throws InterruptedException, KeeperException {
        Stat stat = zookeeper.exists("/lock-/" + waitNode, true);
        if (stat != null) {
            this.pathSign = new CountDownLatch(1);
            this.pathSign.await(waitTime, TimeUnit.MILLISECONDS);
            this.pathSign = null;
        }
        return true;
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
