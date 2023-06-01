# Redis过期提醒

1. Redis 配置
2. Redis 分布式锁：基于自定义LUA脚本实现
3. Redis 分布式锁：基于 spring-integration-redis 实现

redis.conf：

```javascript
/usr/local/redis-5.0.3/redis.conf
# 修改配置文件
notify-keyspace-events Ex
```

相关配置：

- K：keyspace 事件，事件以 keyspace@ 为前缀进行发布
- E：keyevent 事件，事件以 keyevent@ 为前缀进行发布
- g：一般性的，非特定类型的命令，比如del，expire，rename等
- $：字符串特定命令
- l：列表特定命令
- s：集合特定命令
- h：哈希特定命令
- z：有序集合特定命令
- x：过期事件，当某个键过期并删除时会产生该事件
- e：驱逐事件，当某个键因 maxmemore 策略而被删除时，产生该事件
- A：g$lshzxe的别名，因此”AKE”意味着所有事件