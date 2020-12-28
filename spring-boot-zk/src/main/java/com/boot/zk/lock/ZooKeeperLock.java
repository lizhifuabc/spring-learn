package com.boot.zk.lock;

import com.boot.zk.properties.ZkProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * ZooKeeperLock锁
 *
 * @author lizhifu
 * @date 2020/12/28
 */
@Slf4j
public class ZooKeeperLock extends ZooKeeperBase{
    /** lock根目录 */
    private String locksRoot = "/lock";
    /** 加锁标识 */
    private String lockKey;
    /** 超时时间 */
    private Long waitTime;
    /** 加锁节点 */
    private String lockNode;
    /**构造函数*/
    public ZooKeeperLock(ZkProperties zkProperties,String lockKey,Long waitTime) {
        super(zkProperties);
        this.lockKey = lockKey;
        this.waitTime = waitTime;
        try {
            if(super.getZookeeper().exists(locksRoot, false) == null){
                super.getZookeeper().create(locksRoot, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        }catch (Exception e){
            // 如果节点已存在，则创建失败，这里捕获异常，并不阻挡程序正常运行
            log.info("创建根节点{}失败", locksRoot);
        }
    }
    public boolean lock() throws KeeperException, InterruptedException {
        //EPHEMERAL_SEQUENTIAL : 临时顺序节点
        log.info("开始创建临时顺序节点(EPHEMERAL_SEQUENTIAL):{}",lockKey);
        lockNode = super.getZookeeper().create(locksRoot + "/" + lockKey,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        log.info("完成创建临时顺序节点(EPHEMERAL_SEQUENTIAL):{},{}",lockKey,lockNode);
        // 看看刚创建的节点是不是最小的节点
        // locks：10000000000，10000000001，10000000002
        List<String> locks = super.getZookeeper().getChildren(locksRoot, false);
        Collections.sort(locks);
        log.info("如果是最小的节点,则表示取得锁:{},{}",lockKey,lockNode);
        if(lockNode.equals(locksRoot + "/"+ locks.get(0))){
            log.info("是最小的节点获得锁:{},{}",lockKey,lockNode);
            return true;
        }
        log.info("未获得锁:{},{}",lockKey,lockNode);
        //如果不是最小的节点，找到比自己小1的节点
        int previousLockIndex = -1;
        for(int i = 0; i < locks.size(); i++) {
            if(lockNode.equals(locksRoot + "/" + locks.get(i))) {
                previousLockIndex = i - 1;
                break;
            }
        }
        //只是显示，自行拆分
        String preNode = locks.get(previousLockIndex);
        log.info("获取前一个节点:{},{}",lockKey,preNode);
        Stat stat = super.getZookeeper().exists(locksRoot + "/" + preNode, true);
        if (stat != null) {
            super.setPathSemaphore(new CountDownLatch(1));
            //进行等待，前一个节点删除时，监听指定执行
            super.getPathSemaphore().await(waitTime, TimeUnit.MILLISECONDS);
            super.setPathSemaphore(null);
            return true;
        }
        return false;
    }

    /**
     * 释放锁
     */
    public void unlock() {
        try {
            // 删除/locks/10000000001节点
            super.getZookeeper().delete(lockNode, -1);
            super.getZookeeper().close();
            lockNode = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
