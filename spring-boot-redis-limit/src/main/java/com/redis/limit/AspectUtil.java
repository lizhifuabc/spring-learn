package com.redis.limit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.CodeSignature;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取参数值
 *
 * @author lizhifu
 * @date 2020/12/23
 */
public class AspectUtil {
    /**
     * 获取某个Method的参数名称及对应的值
     *
     * @param joinPoint
     * @return Map<参数名称, 参数值></参数名称,参数值>
     */
    public static Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>();
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            param.put(paramNames[i], paramValues[i]);
        }
        return param;
    }
}
