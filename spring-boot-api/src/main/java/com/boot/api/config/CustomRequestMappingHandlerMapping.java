package com.boot.api.config;

import com.boot.api.annotation.ApiVersion;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * 重写部分 RequestMappingHandlerMapping 的方法
 *
 * @author lizhifu
 * @date 2020/12/24
 */
public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        // 扫描类上的 @ApiVersion
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType,ApiVersion.class);
        return null == apiVersion ? super.getCustomTypeCondition(handlerType) : new ApiVersionCondition(apiVersion);
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        // 扫描方法上的 @ApiVersion
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method,ApiVersion.class);
        return null == apiVersion ? super.getCustomMethodCondition(method) : new ApiVersionCondition(apiVersion);
    }
}
