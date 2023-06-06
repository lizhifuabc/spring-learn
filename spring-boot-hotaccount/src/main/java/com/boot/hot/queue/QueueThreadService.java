package com.boot.hot.queue;

import com.boot.hot.dao.AccountHisDao;
import com.boot.hot.dao.param.AccountHisParam;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 队列
 *
 * @author lizhifu
 * @date 2020/12/21
 */
public class QueueThreadService {
    private AtomicInteger index;
    private final AccountThreadRequest[] accountThreadRequests;
    //防止多个线程同时执行。增加一个随机数间隔
    private static final Random r = new Random();
    private static final int delta = 50;
    /** timer线程 **/
    private static ScheduledExecutorService TIMER = new ScheduledThreadPoolExecutor(1);
    private static ExecutorService POOL = Executors.newCachedThreadPool();

    public static final ConcurrentHashMap<Long, AccountRequest> map = new ConcurrentHashMap();

    public QueueThreadService(int threads,Long accountNo) {
        this.accountThreadRequests = new AccountThreadRequest[threads];
        if(threads > 1){
            index = new AtomicInteger();
        }
        for (int i = 0; i < threads; i++) {
            final AccountThreadRequest flushThread = new AccountThreadRequest(accountNo);
            accountThreadRequests[i] = flushThread;
            POOL.submit(flushThread);
            //定时调用 timeOut()方法。
            TIMER.scheduleAtFixedRate(flushThread::timeOut,
                    r.nextInt(delta), flushThread.getInterval(), TimeUnit.MILLISECONDS);
        }
    }

    /**
     * 对 index 取模，保证多线程都能被add
     * @param id
     * @return
     */
    public boolean add(Long id){
        int len = accountThreadRequests.length;
        if(len == 1){
            return accountThreadRequests[0].add(id);
        }
        int mod = index.incrementAndGet() % len;
        return accountThreadRequests[mod].add(id);
    }
}
