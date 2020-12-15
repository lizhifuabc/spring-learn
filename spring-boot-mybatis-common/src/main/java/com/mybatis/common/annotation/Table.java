package com.mybatis.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 实体注解，注明table的名称
 * @author lizhifu
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Table {

    /**
     * table的名称
     * @return ttable的名称
     */
    String value();
}
