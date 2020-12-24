package com.boot.api.annotation;

import java.lang.annotation.*;

/**
 * 接口版本控制
 *
 * @author lizhifu
 * @date 2020/12/24
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {
    /**
     * 版本
     * @return 默认版本
     */
    double value() default 1;
}
