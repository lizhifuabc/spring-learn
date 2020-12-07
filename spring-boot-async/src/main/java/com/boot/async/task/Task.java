package com.boot.async.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * 任务
 *
 * @author lizhifu
 * @date 2020/11/30
 */
@Component
public class Task {
    @Async
    public void run(){
        //线程名称和配置文件内thread-name-prefix保持一致
        System.out.println(Thread.currentThread().getName()+"异步线程任务");
    }

    /**
     * Future是对于具体的Runnable或者Callable任务的执行结果进行取消、查询是否完成、
     * 获取结果的接口。必要时可以通过get方法获取执行结果，该方法会阻塞直到任务返回结果。
     * 判断任务是否完成；
     * 能够中断任务；
     * 能够获取任务执行结果。
     * <p>
     *     isDone方法表示任务是否已经完成，若任务完成，则返回true；
     * </p>
     * <p>
     *     isCancelled方法表示任务是否被取消成功，如果在任务正常完成前被取消成功，则返回 true。
     * </p>
     * <p>
     *     get()方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回；
     * </p>
     * <p>
     *     get(long timeout, TimeUnit unit)用来获取执行结果，如果在指定时间内，还没获取到结果，就直接返回null。
     * </p>
     * <p>
     *     cancel方法用来取消任务，如果取消任务成功则返回true，如果取消任务失败则返回false。
     *     <li>mayInterruptIfRunning表示是否允许取消正在执行却没有执行完毕的任务,如果设置true，则表示可以取消正在执行过程中的任务</li>
     *     <li>如果任务已经完成，则无论mayInterruptIfRunning为true还是false，此方法肯定返回false</li>
     *     <li>如果任务正在执行，若mayInterruptIfRunning设置为true，则返回true，若mayInterruptIfRunning设置为false，则返回false</li>
     *     <li>如果任务还没有执行，则无论mayInterruptIfRunning为true还是false，肯定返回true</li>
     * </p>
     * @return
     */
    @Async
    public Future<String> taskOne(){
        System.out.println(Thread.currentThread().getName()+"异步线程任务");
        return new AsyncResult("taskOne 完成");
    }
}
