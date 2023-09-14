package com.spring.boot.resilience4j.exception;

/**
 * BusinessException:业务异常
 *
 * @author lizhifu
 * @since 2023/9/11
 */
public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
