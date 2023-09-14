package com.spring.boot.resilience4j.exception;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author lizhifu
 * @since 2023/9/14
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 其他所有 Exception
     */
    @ExceptionHandler(value = Exception.class)
    public String ex(HttpServletRequest request, Throwable throwable) {
        log.error("全局异常处理器|[{}] {} [ex] {}", request.getMethod(), request.getRequestURL().toString(),
                throwable.toString());
        return throwable.getLocalizedMessage();
    }
    /**
     * CallNotPermittedException 断路器异常
     */
    @ExceptionHandler(value = CallNotPermittedException.class)
    public String callNotPermittedException(HttpServletRequest request, Throwable throwable) {
        log.error("断路器异常|[{}] {} [ex] {}", request.getMethod(), request.getRequestURL().toString(),
                throwable.toString());
        return throwable.getLocalizedMessage();
    }
}
