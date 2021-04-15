# 可重入锁ReentrantLock
> 某个线程已经获得某个锁，可以再次获取锁而不会出现死锁
## 使用
```java
RLock lock = redisson.getLock("key");
lock.lock();
```

## 测试

```java
@Test
    public void test() throws InterruptedException {
        LockParam lockParam = new LockParam();
        lockParam.setLockValue("lockTest");
        boolean lock = redisLock.lock(lockParam);
        System.out.println("加锁结果"+lock);
        for (int i = 0; i < 5; i++) {
            lock = redisLock.lock(lockParam);
            System.out.println("同一个线程继续加锁："+i);
            System.out.println("同一个线程继续加锁："+lock);
        }
        Thread.sleep(1000*5);
    }
```

