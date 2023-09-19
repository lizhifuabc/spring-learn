package com.spring.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * LogAspect: 日志切面
 *
 * @author lizhifu
 * @since 2023/9/17
 */
@EnableAspectJAutoProxy
@Component
@Aspect
@Slf4j
public class LogAspect {
    /**
     * 切入点.
     */
    @Pointcut("execution(* com.spring.aop.service.*.*(..))")
    private void pointCutMethod() {
    }


    /**
     * 环绕通知.
     *
     * @param pjp pjp
     * @return obj
     * @throws Throwable exception
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        log.info("---------------------环绕通知: 开始---------------------");
        Object o = pjp.proceed();
        log.info("---------------------环绕通知: 结束---------------------");
        return o;
    }

    /**
     * 前置通知.
     */
    @Before("pointCutMethod()")
    public void doBefore() {
        log.info("前置通知");
    }


    /**
     * 后置通知.
     *
     * @param result return val
     */
    @AfterReturning(pointcut = "pointCutMethod()", returning = "result")
    public void doAfterReturning(String result) {
        log.info("后置通知, 返回值: {}", result);
    }

    /**
     * 异常通知.
     *
     * @param e exception
     */
    @AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        log.error("异常通知, 异常: {}", e.getMessage());
    }

    /**
     * 最终通知.
     */
    @After("pointCutMethod()")
    public void doAfter() {
        log.info("最终通知");
    }
}
