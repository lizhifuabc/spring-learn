package com.boot.lombok.example;

import lombok.Synchronized;

/**
 * https://projectlombok.org/features/Synchronized
 * 实现同步访问
 * @author lizhifu
 * @date 2020/12/29
 */
public class SynchronizedExample {
    private final Object readLock = new Object();

    @Synchronized
    public static void hello() {
        System.out.println("world");
    }

    @Synchronized
    public int answerToLife() {
        return 42;
    }

    @Synchronized("readLock")
    public void foo() {
        System.out.println("bar");
    }

    public static void main(String[] args) {
        SynchronizedExample synchronizedExample = new SynchronizedExample();
        SynchronizedExample.hello();
        synchronizedExample.foo();
    }
}
