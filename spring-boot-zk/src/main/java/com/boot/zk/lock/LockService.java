package com.boot.zk.lock;

import com.boot.zk.domain.ZkLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * 锁
 * 分布式锁（基于Zookeeper）
 * 互斥锁
 * 公平锁（监听上一临时顺序节点 + wait() / notifyAll()）
 * 可重入
 * @author lizhifu
 * @date 2020/12/10
 */
public interface LockService {
    /**
     * 加锁
     * @param zkLock 锁参数
     * @return 加锁结果
     * @throws Exception
     */
    LockResult lock(ZkLock zkLock) throws Exception;
    /**
     * 是否锁
     * @param lock
     * @throws Exception
     */
    void releaseLock(InterProcessMutex lock) throws Exception;
}
