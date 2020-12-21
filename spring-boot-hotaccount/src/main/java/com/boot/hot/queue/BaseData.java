package com.boot.hot.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 基础数据
 *
 * @author lizhifu
 * @date 2020/12/21
 */
@Slf4j
public class BaseData<T>{

    private final Integer id;
    /** 队列最大 */
    private final int maxSize;
    /** 间隔 */
    private int interval;
    /** 上一次时间 */
    private volatile long lastExeTime;
    /** 队列 **/
    private final BlockingQueue<T> queue;
    /** 方法执行 **/
    /**
     * 构造函数
     * @param id
     * @param maxSize
     * @param interval
     * @param queueSize
     */
    public BaseData(Integer id, int maxSize, int interval, int queueSize) {
        this.id = id;
        this.maxSize = maxSize;
        this.interval = interval;
        this.lastExeTime = System.currentTimeMillis();
        this.queue = new ArrayBlockingQueue<>(queueSize);
    }
    /**
     * 添加数据:判断是否超时或者超过最大队列
     * @param t 数据
     * @return
     */
    public boolean add(T t){
        boolean result = queue.offer(t);
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
        List<T> temp = new ArrayList<>(maxSize);
        int size = queue.drainTo(temp,maxSize);
        if(size > 0 ){
//            processor.process(temp);
        }
    }
    /**
     * 超时或者队列差熬过最大值
     * @return
     */
    private boolean canExe(){
        return queue.size() > maxSize || System.currentTimeMillis() - lastExeTime > interval;
    }
}
