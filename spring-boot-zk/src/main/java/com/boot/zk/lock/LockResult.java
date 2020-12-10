package com.boot.zk.lock;

import lombok.Data;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * 加锁结果
 *
 * @author lizhifu
 * @date 2020/12/10
 */
@Data
public class LockResult {
    private boolean lock;
    private InterProcessMutex interProcessMutex;
    private LockResult(){}
    public LockResult(boolean lock,InterProcessMutex interProcessMutex){
        this.lock = lock;
        this.interProcessMutex = interProcessMutex;
    }
    public  void releaseLock() throws Exception {
        interProcessMutex.release();
    }
}
