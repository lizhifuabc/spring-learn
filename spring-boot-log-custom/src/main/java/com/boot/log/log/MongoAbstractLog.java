package com.boot.log.log;

import com.boot.log.log.domain.MyLog;
import lombok.extern.slf4j.Slf4j;

/**
 * mongo日志
 *
 * @author lizhifu
 * @date 2020/12/29
 */
@Slf4j
public class MongoAbstractLog extends AbstractLog{
    @Override
    public void saveLog(MyLog myLog) {
        log.info("MongoAbstractLog mongo保存日志 {}",myLog.toString());
    }
}
