package com.boot.mybatis.plugin;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.springframework.stereotype.Component;

import java.sql.*;
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

            Executor executor = (Executor) invocation.getTarget();
            Connection connection = executor.getTransaction().getConnection();
            // 执行 explain 语句，获取 SQL 执行计划,并记录到日志文件或数据库中,方便后续分析
            sqlExplain(mappedStatement.getConfiguration(),mappedStatement,boundSql,connection,parameter);
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


    private void sqlExplain(Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql, Connection connection, Object parameter) {
        // sqlExplain 为 explain + 原始 sql
        String sqlExplain = "EXPLAIN " + boundSql.getSql();
        log.info("执行的 sqlExplain 为：{}",sqlExplain);
        // 通过 StaticSqlSource 封装 explain_sql
        StaticSqlSource sqlSource = new StaticSqlSource(configuration, sqlExplain, boundSql.getParameterMappings());
        // 构建一个新的MappedStatement对象
        MappedStatement.Builder builder = new MappedStatement.Builder(configuration, "explain_sql", sqlSource, SqlCommandType.SELECT);
        MappedStatement queryStatement = builder.build();
        // 设置结果映射和语句类型等信息
        builder.resultMaps(mappedStatement.getResultMaps()).resultSetType(mappedStatement.getResultSetType()).statementType(mappedStatement.getStatementType());
        // 使用ParameterHandler设置SQL参数
        DefaultParameterHandler handler = new DefaultParameterHandler(queryStatement, parameter, boundSql);
        try {
            PreparedStatement stmt = connection.prepareStatement(sqlExplain);
            handler.setParameters(stmt);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                // 记录所有字段值
                StringBuilder explainResult = new StringBuilder();
                ResultSetMetaData metaData = rs.getMetaData();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnValue = rs.getString(i);
                    explainResult.append(columnName).append(": ").append(columnValue).append(", ");
                }
                log.info("Query Plan: {}", explainResult);
            }
        } catch (SQLException e) {
            log.error("Error:{}",e.getMessage());
        }
    }
}
