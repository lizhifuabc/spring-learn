package com.boot.mybatis.annotation;

import java.lang.annotation.*;

/**
 * 动态sql注解
 *
 * @author lizhifu
 * @since 2023/7/16
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicSql {
}
