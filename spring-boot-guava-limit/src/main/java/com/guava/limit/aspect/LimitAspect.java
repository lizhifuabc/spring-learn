package com.guava.limit.aspect;

import com.google.common.util.concurrent.RateLimiter;
import com.guava.limit.annotation.GuavaLimit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * Guava 拦截器
 * 漏桶算法:水（请求）先进入到漏桶里，漏桶以一定的速度出水，当水流入速度过大会直接溢出，可以看出漏桶算法能强行限制数据的传输速率。
 * 令牌桶算法:
 *      系统会以一个恒定的速度往桶里放入令牌，而如果请求需要被处理，则需要先从桶里获取一个令牌，当桶里没有令牌可取时，则拒绝服务。
 *      阻塞等待令牌或者取不到立即返回失败
 * @author lizhifu
 * @date 2020/12/23
 */
@Aspect
@Component
@Slf4j
public class LimitAspect {
    /**
     * 限流器
     */
    private static final ConcurrentMap<String, RateLimiter> RATE_LIMITER_MAP = new ConcurrentHashMap<>();
    /**
     * 切面
     */
    @Pointcut("execution(public * com.guava.limit.controller.*.*(..))")
    public void limit() {
    }
    @Around("limit()")
    public Object pointcut(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        GuavaLimit guavaLimit = method.getAnnotation(GuavaLimit.class);
        //如果存在注解进行限流
        if(guavaLimit != null){
            double qps = guavaLimit.qps();
            if(!RATE_LIMITER_MAP.containsKey(method.getName())){
                RATE_LIMITER_MAP.put(method.getName(),RateLimiter.create(qps));
            }
            log.info("【{}】的QPS设置为: {}", method.getName(), RATE_LIMITER_MAP.get(method.getName()).getRate());
            // 尝试获取令牌
            if (RATE_LIMITER_MAP.get(method.getName()) != null && !RATE_LIMITER_MAP.get(method.getName()).tryAcquire(guavaLimit.timeout(), TimeUnit.MILLISECONDS)) {
                throw new RuntimeException("已经达到限流标准");
            }
        }
        return point.proceed();
    }
}
