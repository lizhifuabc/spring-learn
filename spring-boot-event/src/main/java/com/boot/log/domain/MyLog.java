package com.boot.log.domain;

import lombok.Data;

/**
 * 日志实体
 *
 * @author lizhifu
 * @date 2020/12/29
 */
@Data
public class MyLog {
    private Integer logId;
    private String logInfo;
}
