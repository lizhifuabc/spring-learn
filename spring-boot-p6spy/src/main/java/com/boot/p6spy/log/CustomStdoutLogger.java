package com.boot.p6spy.log;

/**
 * 自定义控制台日志输出
 *
 * @author lizhifu
 * @since 2023/7/17
 */
public class CustomStdoutLogger extends com.p6spy.engine.spy.appender.StdoutLogger{
    @Override
    public void logText(String text) {
        System.out.println("控制台输出sql:" + text);
    }
}
