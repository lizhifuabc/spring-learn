package com.doc.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * SynchronizedDemo
 *
 * @author lizhifu
 * @date 2021/1/15
 */
@Slf4j
public class SynchronizedDemo {
    static int j = 0;
    @SneakyThrows
    public synchronized void increase(){
        Thread.sleep(150);
        log.info("初始值为{}",j);
        for (int k = 0; k < 1000; k++) {
            j++;
        }
        log.info("执行结束完{}",j);
    }
    @SneakyThrows
    public  void increase4(){
        Thread.sleep(150);
        synchronized(SynchronizedDemo.class){
            log.info("初始值为{}",j);
            for (int k = 0; k < 1000; k++) {
                j++;
            }
            log.info("执行结束完{}",j);
        }
    }
    @SneakyThrows
    public  void increase5(){
        Thread.sleep(150);
        synchronized(this){
            log.info("初始值为{}",j);
            for (int k = 0; k < 1000; k++) {
                j++;
            }
            log.info("执行结束完{}",j);
        }
    }
    @SneakyThrows
    public static synchronized void  increase3(){
        Thread.sleep(150);
        log.info("初始值为{}",j);
        for (int k = 0; k < 1000; k++) {
            j++;
        }
        log.info("执行结束完{}",j);
    }
    @SneakyThrows
    public void increase2(){
        Thread.sleep(150);
        log.info("初始值为{}",j);
        for (int k = 0; k < 1000; k++) {
            j++;
        }
        log.info("执行结束完{}",j);
    }
}
