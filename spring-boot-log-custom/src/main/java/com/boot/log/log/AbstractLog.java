package com.boot.log.log;


import com.boot.log.log.domain.MyLog;

/**
 * 自定义日志抽象类
 *
 * @author lizhifu
 * @date 2020/12/29
 */
public abstract class AbstractLog {
    /**
     * 日志保存
     * @param myLog
     */
    public abstract void saveLog(MyLog myLog);
}
