package com.boot.async;

import com.boot.async.task.Task;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 任务测试
 *
 * @author lizhifu
 * @date 2020/11/30
 */
public class TaskTest extends SpringBootAsyncApplicationTest{
    @Resource
    Task task;
    @Test
    public void test() throws InterruptedException {
        task.run();
        TimeUnit.SECONDS.sleep(5);
    }
    @Test
    public void taskOne() throws InterruptedException, ExecutionException {
        Future<String> taskOne = task.taskOne();
        //堵塞线程执行
        taskOne.get();
        System.out.println(taskOne.isDone());
    }
}
