package com.advanced.multi.tenancy.exception;

/**
 * 多租户异常
 *
 * @author lizhifu
 * @since 2023/7/16
 */
public class TenantException extends RuntimeException{
    public TenantException(String message) {
        super(message);
    }

    public TenantException(String message, Throwable cause) {
        super(message, cause);
    }
}
