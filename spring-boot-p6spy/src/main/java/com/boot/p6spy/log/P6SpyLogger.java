package com.boot.p6spy.log;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

/**
 * 自定义打印日志类P6SpyLogger
 * {@link com.p6spy.engine.spy.appender.MultiLineFormat}
 * @author lizhifu
 * @since 2023/7/17
 */
public class P6SpyLogger implements MessageFormattingStrategy {
    /**
     * 格式化日志
     * @param connectionId 连接id
     * @param now 当前时间,毫秒
     * @param elapsed sql执行的耗时。需要注意的是这里的耗时指的是从发送sql到服务器截止到收到服务器响应结果的总耗时，而不是sql本身在服务器的执行时间。
     * @param category 操作的类型，比如查询，更新，commit，rollback等。
     * @param prepared 编译后的sql，不打印具体的参数。
     * @param sql 具体的执行sql，参数占位符会被真正的参数值替换。
     * @param url 当前的数据库连接。
     * @return 格式化后的日志
     */
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return "#" + now + " | 耗时 " + elapsed + "ms | " + category + " | 连接id " + connectionId + "| 数据库连接 " + url + "\n" + prepared + "\n" + sql + ";";
    }
}
