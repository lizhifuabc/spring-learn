package com.boot.mybatis.plugin;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * 基于 MyBatis 插件来统计慢 SQL
 *
 * @author lizhifu
 * @since 2023/7/31
 */
@Component
@Slf4j
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, org.apache.ibatis.session.RowBounds.class, org.apache.ibatis.session.ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class SlowSqlInterceptor implements Interceptor {
    /**
     * 超时阈值，单位为毫秒
     */
    private long threshold;
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = invocation.proceed();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        if (elapsedTime > threshold) {
            Object[] args = invocation.getArgs();
            MappedStatement mappedStatement = (MappedStatement) args[0];
            String sqlId = mappedStatement.getId();
            String methodName = invocation.getMethod().getName();
            // 记录慢 SQL 相关信息，比如 SQL 语句、参数等
            // 在这里可以将这些信息保存到日志文件或数据库中
            // 示例：logSlowSql(invocation, elapsedTime);
            log.info("sqlId：{},methodName：{},执行时间：{}ms",sqlId,methodName,elapsedTime);

            Object parameter = args[1];
            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            String sql = boundSql.getSql();
            log.info("执行的sql为：{}",sql);

            // 执行 explain 语句，获取 SQL 执行计划,并记录到日志文件或数据库中,方便后续分析 TODO
            // 动态 替换 SQL 语句，不重启修复慢 SQL TODO
        }

        return result;
    }
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
    @Override
    public void setProperties(Properties properties) {
        // 从配置中获取阈值，用于设置超时阈值
        // 默认为 1000 毫秒
        threshold = Long.parseLong(properties.getProperty("threshold", "1000"));
    }
}
