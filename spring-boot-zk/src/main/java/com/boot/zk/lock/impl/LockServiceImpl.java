package com.boot.zk.lock.impl;

import com.boot.zk.domain.ZkLock;
import com.boot.zk.lock.LockResult;
import com.boot.zk.lock.LockService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * 锁
 *
 * @author lizhifu
 * @date 2020/12/10
 */
@Service
public class LockServiceImpl implements LockService {
    @Resource
    private CuratorFramework curatorFramework;
    @Override
    public LockResult lock(ZkLock zkLock) throws Exception {
        // Zookeeper利用path创建临时顺序节点，实现公平锁的核心
        // maxLeases=1，表示可以获得分布式锁的线程数量（跨JVM）为1，即为互斥锁
        InterProcessMutex lock = new InterProcessMutex(curatorFramework, zkLock.getLockPath());
        // 限时等待
        LockResult lockResult = new LockResult(lock.acquire(zkLock.getTimeout(),zkLock.getTimeUnit()),lock);
        return lockResult;
    }

    @Override
    public void releaseLock(InterProcessMutex lock) throws Exception {
        lock.release();
    }
}
