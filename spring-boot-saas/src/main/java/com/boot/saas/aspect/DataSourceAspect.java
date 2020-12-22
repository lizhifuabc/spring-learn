package com.boot.saas.aspect;

import com.boot.saas.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 数据源选择器切面
 *
 * @author lizhifu
 * @date 2020/12/22
 */
@Aspect
@Component
public class DataSourceAspect {
    @Pointcut("execution(public * com.boot.saas.controller.*.*(..))")
    public void dataSourcePointCut() {
    }
    @Before("dataSourcePointCut()")
    public void doBefore(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attributes.getRequest();
        String configDatasoure = request.getHeader("datasource-config-name");
        if(null != configDatasoure && !configDatasoure.equals("")){
            DynamicDataSourceContextHolder.setCurrentDatasource(configDatasoure);
        }else {
            DynamicDataSourceContextHolder.setDefaultDatasource();
        }
    }
    /**
     * 后置操作，设置回默认的数据源id
     */
    @AfterReturning("dataSourcePointCut()")
    public void doAfter() {
        DynamicDataSourceContextHolder.clearDataSource();
    }
}
