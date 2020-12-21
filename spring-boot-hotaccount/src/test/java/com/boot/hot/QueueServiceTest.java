package com.boot.hot;

import com.boot.hot.dao.param.AccountHisParam;
import com.boot.hot.enums.DirectionType;
import com.boot.hot.enums.TradeType;
import com.boot.hot.queue.QueueService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * QueryService测试
 *
 * @author lizhifu
 * @date 2020/12/21
 */
@SpringBootTest
public class QueueServiceTest {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 50, 2, TimeUnit.MINUTES,new ArrayBlockingQueue<Runnable>(5000));
    private Long accountNo = 100000L;
    private BigDecimal amount = new BigDecimal(10.12);
    @Resource
    QueueService queueService;
    @Test
    public void test() throws InterruptedException {
        ThreadTask threadTask = new ThreadTask();
        for(int i = 0;i < 50;i++) {
            executor.execute(threadTask);
        }
        Thread.sleep(5000L);
        System.out.println("结束");
    }

    private class ThreadTask implements Runnable{
        public ThreadTask() {
        }
        public void run() {
            System.out.println(Thread.currentThread().getName()+":"+System.currentTimeMillis());
            Random random = new Random();
            AccountHisParam accountHisParam = new AccountHisParam();
            accountHisParam.setAccountNo(accountNo);
            accountHisParam.setAmount(new BigDecimal(random.nextDouble()));
            accountHisParam.setTradeFlowNo(UUID.randomUUID().toString());
            accountHisParam.setTradeSignNo(100000L);
            accountHisParam.setTradeType(TradeType.ORDER);
            accountHisParam.setDirection(DirectionType.ADD);
            queueService.add(accountHisParam);
        }
    }
}
