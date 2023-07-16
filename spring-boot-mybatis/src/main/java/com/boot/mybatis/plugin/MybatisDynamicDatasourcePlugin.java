package com.boot.mybatis.plugin;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.apache.ibatis.executor.statement.StatementHandler;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 基于 Mybatis 的拦截器实现动态数据源
 * 替换 sql 语句，将表替换成需要操作的表
 * @author lizhifu
 * @since 2023/6/13
 */
@Slf4j
//@Component
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class MybatisDynamicDatasourcePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String shardKey = "userName";
        String shardValue = null;
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object params = args[1];
        log.info(params instanceof Map?"Map":"Bean");
        if (params instanceof Map) {
            Map<String, Object> paMap = (Map<String, Object>) params;
            //Map对象中获取 shardValue
            for (Map.Entry<String, Object> entry : paMap.entrySet()) {
                String key = entry.getKey();
                if (key.equals(shardKey)){
                    shardValue = entry.getValue().toString();
                    break;
                }
                Object value = entry.getValue();
                for (Field declaredField : value.getClass().getDeclaredFields()) {
                    if (declaredField.getName().equals(shardKey)) {
                        declaredField.setAccessible(true);
                        shardValue = declaredField.get(value).toString();
                        break;
                    }
                }
                if (shardValue != null){
                    break;
                }
            }
        } else {
            //Bean对象中获取 shardValue
            for (Field declaredField : params.getClass().getDeclaredFields()) {
                if (declaredField.getName().equals(shardKey)) {
                    declaredField.setAccessible(true);
                    shardValue = declaredField.get(params).toString();
                    break;
                }
            }
        }
        log.info("shardValue:{}",shardValue);
        return invocation.proceed();
    }
}
