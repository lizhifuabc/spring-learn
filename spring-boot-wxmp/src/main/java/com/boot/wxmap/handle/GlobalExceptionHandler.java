package com.boot.wxmap.handle;

import com.boot.wxmap.base.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理
 *
 * @author lizhifu
 * @date 2021/1/25
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * java bean参数异常
     * @param req
     * @param e
     * @return
     * @throws BindException
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public R handlerBindException(HttpServletRequest req, BindException e){
        log.error("BindException requestURI:{} paramName:{} msg:{}", req.getRequestURI(), e.getObjectName(), e.getMessage());
        return new R().error(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
    /**
     * 单个参数参数异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public R handleConstraintViolationException(HttpServletRequest req, ConstraintViolationException e) {
        log.error(String.format("ConstraintViolationException RequestURI:%s 异常信息:{}", req.getRequestURI()), e.getMessage());
        return new R().error(e.getMessage(),HttpStatus.BAD_REQUEST.value());
    }
    /**
     * 整体捕获
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R handlerException(HttpServletRequest req, Exception e) {
        log.error(String.format("Exception requestURI:%s", req.getRequestURI()), e);
        return new R().error(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
