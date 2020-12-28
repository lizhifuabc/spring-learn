package com.boot.zk.lock;

import com.boot.zk.properties.ZkProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * ZooKeeperBase：watcher
 *
 * @author lizhifu
 * @date 2020/12/28
 */
@Slf4j
public class ZooKeeperBase implements Watcher {
    /** 等待连接建立成功的信号 */
    private CountDownLatch connectedSemaphore = new CountDownLatch(1);
    /** 节点事件信号 */
    private CountDownLatch pathSemaphore;
    /** ZooKeeper 客户端 */
    private ZooKeeper zookeeper = null;
    private ZooKeeperBase(){}
    /** 构造函数 */
    public ZooKeeperBase(ZkProperties zkProperties){
        try {
            this.zookeeper = new ZooKeeper(zkProperties.getServer(), zkProperties.getSessionTimeoutMs(),this);
            connectedSemaphore.await();
            log.info("ZooKeeperBase 建立完成");
        } catch (IOException | InterruptedException e) {
            log.error("ZooKeeperBase 建立链接失败 IOException InterruptedException"+e);
        }
    }
    /** 读取ZooKeeper对象 */
    public ZooKeeper getZookeeper() {
        return zookeeper;
    }
    /** 读取节点控制对象 */
    public CountDownLatch getPathSemaphore() {
        return pathSemaphore;
    }
    /** 设置节点控制对象 */
    public void setPathSemaphore(CountDownLatch pathSemaphore) {
        this.pathSemaphore = pathSemaphore;
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        // 连接状态发生变化
        log.info("ZooKeeperBase 连接状态 {} 事件类型{}",watchedEvent.getState(),watchedEvent.getType());
        if(Event.EventType.None.equals(watchedEvent.getType())){
            if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                connectedSemaphore.countDown();
            }
        }
        if (this.pathSemaphore != null) {
            this.pathSemaphore.countDown();
        }
        if (Event.EventType.NodeCreated.equals(watchedEvent.getType())) {
            processNodeCreated(watchedEvent);
        } else if (Event.EventType.NodeDeleted.equals(watchedEvent.getType())) {
            processNodeDeleted(watchedEvent);
        } else if (Event.EventType.NodeDataChanged.equals(watchedEvent.getType())) {
            processNodeDataChanged(watchedEvent);
        } else if (Event.EventType.NodeChildrenChanged.equals(watchedEvent.getType())) {
            processNodeChildrenChanged(watchedEvent);
        }
    }
    /**
     * 处理事件: NodeCreated
     *
     * @param event
     */
    protected void processNodeCreated(WatchedEvent event) {}

    /**
     * 处理事件: NodeDeleted
     *
     * @param event
     */
    protected void processNodeDeleted(WatchedEvent event) {}

    /**
     * 处理事件: NodeDataChanged
     *
     * @param event
     */
    protected void processNodeDataChanged(WatchedEvent event) {}

    /**
     * 处理事件: NodeChildrenChanged
     *
     * @param event
     */
    protected void processNodeChildrenChanged(WatchedEvent event) {}
}
