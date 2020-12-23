package com.guava.limit.annotation;

import java.lang.annotation.*;

/**
 * GuavaLimit
 *
 * @author lizhifu
 * @date 2020/12/23
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GuavaLimit {
    /**
     * qps:每秒请求数，就是说服务器在一秒的时间内处理了多少个请求及每秒只发出N个令牌
     * @return
     */
    double qps();
    /**
     * 超时时长:单位毫秒
     */
    int timeout();
}
