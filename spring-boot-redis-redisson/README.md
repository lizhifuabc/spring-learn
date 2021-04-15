# Redisson
## 分布式锁
### LUA脚本分析:类名称(RedissonLock)
```java
<T> RFuture<T> tryLockInnerAsync(long waitTime, long leaseTime, TimeUnit unit, long threadId, RedisStrictCommand<T> command) {
        return evalWriteAsync(getName(), LongCodec.INSTANCE, command,
                "if (redis.call('exists', KEYS[1]) == 0) then " +
                        "redis.call('hincrby', KEYS[1], ARGV[2], 1); " +
                        "redis.call('pexpire', KEYS[1], ARGV[1]); " +
                        "return nil; " +
                        "end; " +
                        "if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then " +
                        "redis.call('hincrby', KEYS[1], ARGV[2], 1); " +
                        "redis.call('pexpire', KEYS[1], ARGV[1]); " +
                        "return nil; " +
                        "end; " +
                        "return redis.call('pttl', KEYS[1]);",
                Collections.singletonList(getName()), unit.toMillis(leaseTime), getLockName(threadId));
    }
```

```java
getName()
```

逻辑锁名称：即代码传入的锁名称

```java
unit.toMillis(leaseTime)
```

锁过期时间：默认为` this.internalLockLeaseTime = commandExecutor.getConnectionManager().getCfg().getLockWatchdogTimeout();`30 * 1000

```java
getLockName(threadId);
long threadId = Thread.currentThread().getId();  
```

锁对应的线程的名称：UUID+":"threadId(SingleConnectionManager)



Lua脚本中的执行分为以下三步：

1. exists检查redis中是否存在锁名称；

   如果不存在，则获取成功；

   同时把逻辑锁名称KEYS[1]，线程级别的锁名称ARGV[2]，value=1,设置到redis。并设置逻辑锁名称的过期时间ARGV[2]，返回；

   ```javascript
   redis 127.0.0.1:6379> HINCRBY KEY_NAME FIELD_NAME INCR_BY_NUMBER 
   ```

2. 如果检查到存在KEYS[1],ARGV[2],则说明获取成功，此时会自增对应的value值，记录重入次数；并更新锁的过期时间

3. key不存，直接返回key的剩余过期时间（-2）

### [可重入锁ReentrantLock](./ReentrantLock.md)

