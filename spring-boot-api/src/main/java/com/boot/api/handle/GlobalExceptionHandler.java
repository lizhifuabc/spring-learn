package com.boot.api.handle;

import com.boot.api.base.R;
import com.boot.api.base.ReturnCode;
import com.boot.api.exception.CustomException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author lizhifu
 * @date 2020/12/24
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     *  自定义异常
     * @param request
     * @param customException
     * @return
     */
    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public R handlerCustomException(HttpServletRequest request, CustomException customException) {
        log.error(String.format("CustomException RequestURI:%s", request.getRequestURI()), customException);
        return new R().error(customException.getMsg(),customException.getCode());
    }
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public R handlerHttpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
        log.error(String.format("HttpRequestMethodNotSupportedException RequestURI:%s 异常信息:{}", request.getRequestURI()), e.getMessage());
        return new R().error(e.getMessage(),ReturnCode.PARAM_ERROR.getCode());
    }

    /**
     * spring 参数转换异常。例如i=1,传入了i=a
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public R handlerMethodArgumentTypeMismatchException(HttpServletRequest req, MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException RequestURI:{} 方法:{} 参数:{} 异常信息:{}", req.getRequestURI(),e.getParameter().getMethod().getName(),e.getName(), e.getMessage());
        return new R().error("参数" + e.getName() + "类型不正确",ReturnCode.PARAM_ERROR.getCode());
    }

    /**
     * 404:注意配置文件要同步配置
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    public R handlerNoHandlerFoundException(HttpServletRequest req, NoHandlerFoundException e) {
        log.error("NoHandlerFoundException RequestURI:{} httpMethod:{} headers:{} 异常信息:{}", req.getRequestURI(),e.getHttpMethod(),e.getHeaders(),e.getMessage());
        return new R().error(ReturnCode.NOT_FOUND.getMsg(),ReturnCode.NOT_FOUND.getCode());
    }

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
        return new R().error(e.getMessage(),ReturnCode.PARAM_ERROR.getCode());
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
        return new R().error(e.getMessage(),ReturnCode.PARAM_ERROR.getCode());
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
        return new R().error(ReturnCode.SYSTEM_ERROR.getMsg(),ReturnCode.SYSTEM_ERROR.getCode());
    }
}
