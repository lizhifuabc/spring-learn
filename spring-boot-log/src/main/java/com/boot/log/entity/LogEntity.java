package com.boot.log.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 日志实体
 *
 * @author lizhifu
 * @date 2020/12/1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogEntity {
    /**
     * 线程id
     */
    private String threadId;
    /**
     * 线程名称
     */
    private String threadName;
    /**
     * ip
     */
    private String ip;
    /**
     * url
     */
    private String url;
    /**
     * http方法 GET POST PUT DELETE PATCH
     */
    private String httpMethod;
    /**
     * 类方法
     */
    private String classMethod;
    /**
     * 请求参数
     */
    private Object requestParams;
    /**
     * 返回值
     */
    private Object result;
    /**
     * 接口耗时
     */
    private Long timeCost;
    /**
     * 操作系统
     */
    private String os;
    /**
     * 浏览器
     */
    private String browser;
    /**
     * user-agent
     */
    private String userAgent;

}
