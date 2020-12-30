package com.boot.sharding.interceptor;

import com.boot.sharding.annotation.ShardingStrategy;
import com.boot.sharding.strategy.TableShardingStrategy;
import com.boot.sharding.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.List;

/**
 * 动态解析SQL和更改SQL
 * 实现org.apache.ibatis.plugin.Interceptor接口。
 * 添加拦截器注解org.apache.ibatis.plugin.Intercepts。
 * Executor：拦截执行器的方法。
 * ParameterHandler：拦截参数的处理。
 * ResultHandler：拦截结果集的处理。
 * StatementHandler：拦截Sql语法构建的处理。
 *
 * 拦截StatementHandler类中参数类型为Statement的prepare方法（prepare=在预编译SQL前加入修改的逻辑）
 * @author lizhifu
 * @date 2020/12/29
 */
@Slf4j
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class,Integer.class })})
public class MybatisInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        String id = mappedStatement.getId();
        Class clz = Class.forName(id.substring(0, id.lastIndexOf(".")));
        Annotation annotation = clz.getAnnotation(ShardingStrategy.class);
        if(null == annotation){
            return invocation.proceed();
        }
        log.info("获取到ShardingStrategy注解，开始进行sql解析");
        ShardingStrategy shardingStrategy = (ShardingStrategy)annotation;
        String logicTable = shardingStrategy.logicTable();
        String shardingKey = shardingStrategy.shardingKey();

        BoundSql boundSql = statementHandler.getBoundSql();
        log.info("[原始SQL] sql:{}", boundSql.getSql());
        String originSql = boundSql.getSql();
        //获取字段?的位置
        int index = StrUtil.sumNumber(originSql.split(shardingKey)[0],"\\?");
        Object parameterObject = boundSql.getParameterObject();
        MetaObject metaParamObject = mappedStatement.getConfiguration().newMetaObject(parameterObject);
        List<ParameterMapping> list = boundSql.getParameterMappings();
        ParameterMapping parameterMapping = list.get(index);
        String propertyName = parameterMapping.getProperty();
        Object obj;
        if (metaParamObject.hasGetter(propertyName)) {
            obj = metaParamObject.getValue(propertyName);
        }else {
            obj = boundSql.getAdditionalParameter(propertyName);
        }
        Class<? extends TableShardingStrategy> tableShardingStrategy = shardingStrategy.tableShardingStrategy();
        String tableName = tableShardingStrategy.newInstance().getTableName(logicTable,obj);
        log.info("[表名称] tableName:{}", tableName);
        String sql = originSql.replaceAll(logicTable, tableName);
        Field field = boundSql.getClass().getDeclaredField("sql");
        field.setAccessible(true);
        field.set(boundSql, sql);
        log.info("分库分表之后SQL：{}", sql);
        return invocation.proceed();
    }
}