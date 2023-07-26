package com.spring.boot.web.advice;

import com.spring.boot.web.domain.Response;
import com.spring.boot.web.domain.ResponseFactory;
import jakarta.annotation.Resource;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 返回值处理
 *
 * @author lizhifu
 * @since 2023/7/26
 */
@ControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Resource
    private ResponseFactory responseFactory;
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null) {
            return responseFactory.newSuccessInstance();
        } else if (body instanceof Response) {
            return body;
        } else {
            return responseFactory.newSuccessInstance(body);
        }
    }
}
