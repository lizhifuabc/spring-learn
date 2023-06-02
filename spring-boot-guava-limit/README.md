# 基于 guava 限流

RateLimiter 的主要方法有:
- acquire():获取一个许可,如果无法获取会一直阻塞。
- acquire(int permits):获取指定数目的许可,如果无法获取会一直阻塞。
- tryAcquire():尝试获取一个许可,如果获取成功返回 true,否则返回 false。
- tryAcquire(int permits):尝试获取指定数目的许可,获取成功返回获取到的许可数,否则返回 0。

Guava RateLimiter 使用的算法是漏桶算法(Leaky Bucket Algorithm)。
漏桶算法的原理是:有一个桶,以一个固定的速率往桶里添加令牌,桶有一个最大容量。如果要处理一个请求,需要从桶里移除一个令牌。如果桶里有令牌,请求被处理,并从桶中移除一个令牌;如果桶空了,请求被阻塞或拒绝。
Guava RateLimiter 的实现就是一个漏桶:
- 桶的大小(容量)由上限 permits 确定。
- 向桶中添加令牌的速率由每秒生成令牌数(rate)确定。
- 想要处理一个请求,需要调用 acquire() 从桶中获取一个令牌。
- 如果桶中有令牌,请求被处理,令牌被移除;如果桶空,请求会被阻塞,直到有新的令牌生成。

地址：
http://localhost:8080/learn/