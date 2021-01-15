package com.doc.demo;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * synchronized
 *
 * @author lizhifu
 * @date 2021/1/14
 */
public class SynchronizedDemoTest {
    /**
     * 直接修饰某个实例方法
     */
    @Test
    public void increase2() throws Exception {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        Thread thread1 = new Thread(() -> synchronizedDemo.increase2());
        Thread thread2 = new Thread(() -> synchronizedDemo.increase2());
        Thread thread3 = new Thread(() -> synchronizedDemo.increase2());
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(1000);
        System.out.println(SynchronizedDemo.j);
    }
    @Test
    public void increase() throws Exception {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        Thread thread1 = new Thread(() -> synchronizedDemo.increase());
        Thread thread2 = new Thread(() -> synchronizedDemo.increase());
        Thread thread3 = new Thread(() -> synchronizedDemo.increase());
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(1000);
        System.out.println(SynchronizedDemo.j);
    }
    @Test
    public void increase3() throws Exception {
        SynchronizedDemo synchronizedDemo1 = new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo2 = new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo3 = new SynchronizedDemo();

        Thread thread1 = new Thread(() -> synchronizedDemo1.increase());
        Thread thread2 = new Thread(() -> synchronizedDemo2.increase());
        Thread thread3 = new Thread(() -> synchronizedDemo3.increase());
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(1000);
        System.out.println(SynchronizedDemo.j);
    }
    @Test
    public void increase4() throws Exception {
        SynchronizedDemo synchronizedDemo1 = new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo2 = new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo3 = new SynchronizedDemo();

        Thread thread1 = new Thread(() -> synchronizedDemo1.increase3());
        Thread thread2 = new Thread(() -> synchronizedDemo2.increase3());
        Thread thread3 = new Thread(() -> synchronizedDemo3.increase3());
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(1000);
        System.out.println(SynchronizedDemo.j);
    }
    @Test
    public void increase5() throws Exception {
        SynchronizedDemo synchronizedDemo1 = new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo2 = new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo3 = new SynchronizedDemo();

        Thread thread1 = new Thread(() -> synchronizedDemo1.increase4());
        Thread thread2 = new Thread(() -> synchronizedDemo2.increase4());
        Thread thread3 = new Thread(() -> synchronizedDemo3.increase4());
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(1000);
        System.out.println(SynchronizedDemo.j);
    }
    @Test
    public void increase6() throws Exception {
        SynchronizedDemo synchronizedDemo1 = new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo2 = new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo3 = new SynchronizedDemo();

        Thread thread1 = new Thread(() -> synchronizedDemo1.increase5());
        Thread thread2 = new Thread(() -> synchronizedDemo2.increase5());
        Thread thread3 = new Thread(() -> synchronizedDemo3.increase5());
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(1000);
        System.out.println(SynchronizedDemo.j);
    }
}
