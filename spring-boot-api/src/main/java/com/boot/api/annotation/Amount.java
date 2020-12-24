package com.boot.api.annotation;

import com.boot.api.validator.AmountValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义金额注解
 *
 * @author lizhifu
 * @date 2020/12/24
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= AmountValidator.class)
public @interface Amount {
    String message() default "金额格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
