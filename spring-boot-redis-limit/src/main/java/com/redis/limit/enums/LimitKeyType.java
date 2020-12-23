package com.redis.limit.enums;

/**
 * 限流key的类型
 *
 * @author lizhifu
 * @date 2020/12/23
 */
public enum LimitKeyType {
    /**
     * 从request内获取数据
     */
    REQUEST_PARAM,
    /**
     * 静态数据
     */
    STATIC_PARAM
}
