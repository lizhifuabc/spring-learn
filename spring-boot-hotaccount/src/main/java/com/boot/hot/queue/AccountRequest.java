package com.boot.hot.queue;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 请求数据
 *
 * @author lizhifu
 * @date 2020/12/21
 */
@Slf4j
@Data
public class AccountRequest {
    private Long accountNo;
    /** 队列 **/
    private LinkedBlockingQueue<Long> queue;
    /** 队列最大 */
    private final int maxSize = 20;
    /** 间隔 */
    private int interval = 40;
    /** 上一次时间 */
    private volatile long lastExeTime  = System.currentTimeMillis();;
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
            log.info("开始执行{}",size);
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
