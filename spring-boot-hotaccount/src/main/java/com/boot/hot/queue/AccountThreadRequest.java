package com.boot.hot.queue;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * 请求数据
 *
 * @author lizhifu
 * @date 2020/12/21
 */
@Slf4j
@Data
public class AccountThreadRequest implements Runnable{
    private volatile Thread writer;
    /** 线程名称 **/
    private final Long accountNo;
    /** 队列 **/
    private LinkedBlockingQueue<Long> queue;
    /** 队列最大 */
    private final int maxSize = 20;
    /** 间隔 */
    private int interval = 40;
    /** 上一次时间 */
    private volatile long lastExeTime  = System.currentTimeMillis();;

    public AccountThreadRequest(Long accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * 添加数据:判断是否超时或者超过最大队列
     * offer() 方法会返回 false。因此就可以在程序中进行有效的判断！
     * @param id 数据
     * @return
     */
    public boolean add(Long id){
        boolean result = queue.offer(id);
        exe();
        return result;
    }
    /**
     * 执行
     */
    private void exe(){
        if(!canExe()){
            return;
        }
        lastExeTime = System.currentTimeMillis();
        List<Long> temp = new ArrayList<>(maxSize);
        int size = queue.drainTo(temp,maxSize);
        if(size > 0 ){
            log.info("队列长度为{}",queue.size());
            log.info("开始异步执行{}",size);
        }
    }
    /**
     * 超时或者队列差熬过最大值
     * @return
     */
    private boolean canExe(){
        return queue.size() > maxSize || System.currentTimeMillis() - lastExeTime > interval;
    }

    /**
     * 是否超时
     */
    public void timeOut(){
        //超过两次提交超过时间间隔
        if(System.currentTimeMillis() - lastExeTime >= interval){
            start();
        }
    }
    /**
     * 当前的数据是否大于提交的条件
     */
    private void maxSize(){
        if(queue.size() >= maxSize){
            start();
        }
    }
    /**
     * 解除线程的阻塞
     */
    private void start(){
        LockSupport.unpark(writer);
    }
    @Override
    public void run() {
        writer = Thread.currentThread();
        writer.setName(String.valueOf(accountNo));
        while (!writer.isInterrupted()){
            while (!canExe()){
                //如果线程没有被打断，且不达到执行的条件，则阻塞线程
                LockSupport.park(this);
            }
            //执行方法
            exe();
        }
    }
}
