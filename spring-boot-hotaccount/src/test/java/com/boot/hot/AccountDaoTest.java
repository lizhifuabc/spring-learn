package com.boot.hot;

import com.boot.hot.dao.AccountDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * AccountDao
 *
 * @author lizhifu
 * @date 2020/12/18
 */
@SpringBootTest
public class AccountDaoTest {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 50, 2, TimeUnit.MINUTES,new ArrayBlockingQueue<Runnable>(5000));
    private Long accountNo = 100000L;
    private BigDecimal amount = new BigDecimal(10.12);
    @Resource
    AccountDao accountDao;
    @Test
    public void add() throws InterruptedException {
        ThreadTask threadTask = new ThreadTask();
        for(int i = 0;i < 3000;i++) {
            executor.execute(threadTask);
        }
        Thread.sleep(10000000L);
    }
    @Test
    public void reduce() throws InterruptedException {
        ThreadTaskReduce threadTask = new ThreadTaskReduce();
        for(int i = 0;i < 3000;i++) {
            executor.execute(threadTask);
        }
        Thread.sleep(10000000L);
    }
    private class ThreadTask implements Runnable{
        public ThreadTask() {
        }
        public void run() {
            System.out.println(Thread.currentThread().getName()+":"+System.currentTimeMillis());
            accountDao.add(amount,accountNo);
        }
    }
    private class ThreadTaskReduce implements Runnable{
        public ThreadTaskReduce() {
        }
        public void run() {
            int i = accountDao.reduce(amount,accountNo);
            System.out.println("扣款结果："+i+":"+Thread.currentThread().getName()+":"+System.currentTimeMillis());
        }
    }
}
