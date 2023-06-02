package com.boot.log.log;

import com.boot.log.domain.MyLog;
import com.boot.log.log.impl.DataSourceAbstractLog;
import com.google.common.eventbus.Subscribe;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * 日志监听
 *
 * @author lizhifu
 * @date 2020/12/29
 */
@Component
public class LogEventListener {
    @Resource
    private AbstractLog abstractLog;
    @Subscribe
    public void onMessageEvent(MyLog myLog) {
        // 保存日志
        abstractLog.saveLog(myLog);
    }
    /**
     * 默认实现为数据库存储
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public AbstractLog abstractLog(){
        return new DataSourceAbstractLog();
    }
}
