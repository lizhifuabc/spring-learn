package com.mybatis.provider.core.annotation;

import java.lang.annotation.*;

/**
 * 对象属性不映射到数据库注解（可作用于字段上，或类型指定哪些字段不映射）
 *
 * @author lizhifu
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoColumn {
    /**
     * 作用于类型指定不需要映射的字段名数组
     */
    String[] fields() default {};
}
