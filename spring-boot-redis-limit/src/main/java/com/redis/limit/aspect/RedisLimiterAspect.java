package com.redis.limit.aspect;

import com.alibaba.fastjson.JSONObject;
import com.redis.limit.AspectUtil;
import com.redis.limit.annotation.RedisLimit;
import com.redis.limit.enums.LimitKeyType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * Redis 拦截器
 *
 * @author lizhifu
 * @date 2020/12/22
 */
@Aspect
@Component
@Slf4j
public class RedisLimiterAspect {
    private final static String REDIS_LIMIT_KEY_PREFIX = "limit:";
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisScript<Long> redisLuaScript;
    /**
     * 切面
     */
    @Pointcut("@annotation(com.redis.limit.annotation.RedisLimits)")
    public void redisLimit() {
    }
    @Around("redisLimit()")
    public Object pointcut(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        RedisLimit[] redisLimits = method.getAnnotationsByType(RedisLimit.class);
        //如果存在注解进行限流
        if(redisLimits != null){
            for (RedisLimit redisLimit : redisLimits) {
                int max = redisLimit.max();
                int timeout = redisLimit.timeout();
                String key = redisLimit.key();
                LimitKeyType keyType = redisLimit.keyType();
                if(keyType.equals(LimitKeyType.REQUEST_PARAM)){
                    log.info("参数值为{}", AspectUtil.getNameAndValue(point));
                    key = JSONObject.toJSONString(AspectUtil.getNameAndValue(point).get(key));
                }
                boolean limited = canLimit(key,max,timeout);
                if (limited) {
                    throw new RuntimeException("已经达到限流标准");
                }
            }
        }
        return point.proceed();
    }

    /**
     * 针对某个key使用lua脚本进行限流
     * 使用lua优点，可以保证多个命令是一次行传输到Redis服务器并且是串行执行的，保证串行执行的命令中不行插入其他命令，防止并发问题
     * 步骤：
     * 1、判断key是否存在，如果不存在，保存该key，设置值为1，设置多少毫秒（n）最多进行几次（m)
     * 2、如果值存在，先判断key是否超时了，如果超时则删除，并重新执行步骤1，如果key未超时，则判断是否超过n毫秒最多m次的限制
     * @param key
     * @param max
     * @param timeout
     * @return
     */
    private boolean canLimit(String key, int max, int timeout) {
        List<String> keys = Collections.singletonList(REDIS_LIMIT_KEY_PREFIX+key);
        //String.valueOf java.lang.Integer cannot be cast to java.lang.String
        Long executeTimes = stringRedisTemplate.execute(redisLuaScript,keys,String.valueOf(max),String.valueOf(timeout));
        if (executeTimes != null) {
            if (executeTimes == 0) {
                log.error("【{}】在单位时间 {} 毫秒内已达到访问上限，当前接口上限 {}", key,timeout, max);
                return true;
            } else {
                log.info("【{}】在单位时间 {} 毫秒内访问 {} 次", key,timeout, executeTimes);
                return false;
            }
        }
        return false;
    }
}
