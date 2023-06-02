package com.boot.log.log.impl;

import com.boot.log.domain.MyLog;
import com.boot.log.log.AbstractLog;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据库保存日志
 *
 * @author lizhifu
 * @date 2020/12/29
 */
@Slf4j
public class DataSourceAbstractLog extends AbstractLog {
    @Override
    public void saveLog(MyLog myLog) {
        log.info("DataSourceAbstractLog 数据库保存日志 {}",myLog.toString());
    }
}
