package com.boot.log.aspectj;

import com.boot.log.entity.LogEntity;
import com.boot.log.util.ArrayUtil;
import com.boot.log.util.IpUtils;
import com.google.common.collect.Maps;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * 基于注解的日志拦截器，注解可以自定义很多内容，例如模块、描述等等内容
 * <li>@Around:
 * <p>既可以在目标方法之前织入增强动作，也可以在执行目标方法之后织入增强动作；</p>
 * <p>可以改变执行目标方法的参数值，也可以改变执行目标方法之后的返回值； 当需要改变目标方法的返回值时，只能使用Around方法；</p>
 * <p>可以决定目标方法在什么时候执行，如何执行，甚至可以完全阻止目标目标方法的执行；</p>
 * <li>虽然Around功能强大，但通常需要在线程安全的环境下使用。因此，
 * 如果使用普通的Before、AfterReturing增强方法就可以解决的事情，就没有必要使用Around增强处理了。
 * @author lizhifu
 * @date 2020/12/1
 */
@Aspect
@Component
@Slf4j
public class LogExecutionAspectj {
    /**
     * 配置切入点
     */
    @Pointcut("execution(public * com.part.log.controller.*Controller.*(..))")
    public void logPointCut()
    {
    }
    /**
     * 环绕操作
     *
     * @param point 切入点
     * @return 原方法返回值
     * @throws Throwable 异常信息
     */
    @Around("logPointCut()")
    public Object aroundLog(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        //开始时间
        long startTime = System.currentTimeMillis();
        //方法执行
        Object result = point.proceed();
        //UserAgent
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        final LogEntity logEntity = LogEntity.builder()
                .threadId(Long.toString(Thread.currentThread().getId()))
                .threadName(Thread.currentThread().getName())
                .ip(IpUtils.getIp(request))
                .url(request.getRequestURL().toString())
                .classMethod(String.format("%s.%s", point.getSignature().getDeclaringTypeName(),
                        point.getSignature().getName()))
                .httpMethod(request.getMethod())
                .requestParams(getNameAndValue(point))
                .result(result)
                .timeCost(System.currentTimeMillis() - startTime)
                .userAgent(header)
                .browser(userAgent.getBrowser().toString())
                .os(userAgent.getOperatingSystem().toString()).build();
        log.info("Request Log Info : {}", logEntity.toString());
        return result;
    }
    /**
     *  获取方法参数名和参数值
     * @param joinPoint
     * @return
     */
    private Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {

        final Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        final String[] names = methodSignature.getParameterNames();
        final Object[] args = joinPoint.getArgs();

        if (ArrayUtil.isEmpty(names) || ArrayUtil.isEmpty(args)) {
            return Collections.emptyMap();
        }
        if (names.length != args.length) {
            log.warn("{}方法参数名和参数值数量不一致", methodSignature.getName());
            return Collections.emptyMap();
        }
        Map<String, Object> map = Maps.newHashMap();
        for (int i = 0; i < names.length; i++) {
            map.put(names[i], args[i]);
        }
        return map;
    }
}
