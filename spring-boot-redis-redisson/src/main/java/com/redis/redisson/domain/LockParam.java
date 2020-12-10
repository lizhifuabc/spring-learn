package com.redis.redisson.domain;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * LockParam 参数
 *
 * @author lizhifu
 * @date 2020/12/10
 */
@Data
public class LockParam {
    /**
     * 分布式锁的键
     */
    private String lockValue;

    /**
     * 锁释放时间，默认五秒
     */
    private long timeout = 5 * 1000;

    /**
     * 时间格式，默认：毫秒
     */
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;
}
