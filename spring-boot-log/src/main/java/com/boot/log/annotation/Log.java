package com.boot.log.annotation;

import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 *
 * @author lizhifu
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log
{
    /**
     * 模块 
     */
    public String title() default "";
}