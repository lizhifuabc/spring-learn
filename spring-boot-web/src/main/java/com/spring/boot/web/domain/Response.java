package com.spring.boot.web.domain;

/**
 * 数据返回
 *
 * @author lizhifu
 * @since 2023/7/26
 */
public interface Response {
    /**
     * 响应码
     *
     * @param responseStatus 响应码
     */
    void setStatus(ResponseStatus responseStatus);

    /**
     * 获取响应码
     *
     * @return 响应码
     */
    ResponseStatus getStatus();

    /**
     * 设置响应数据.
     *
     * @param data 设置的响应数据.
     */
    void setData(Object data);

    /**
     * 获得响应数据.
     *
     * @return 响应数据
     */
    Object getData();
}
