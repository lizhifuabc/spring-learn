package com.spring.boot.web.domain;

/**
 * 响应状态
 *
 * @author lizhifu
 * @since 2023/7/26
 */
public interface ResponseStatus {
    /**
     * 设置响应码.
     *
     * @param code 设置的响应码.
     */
    void setCode(String code);

    /**
     * 获得响应码.
     *
     * @return 响应码
     */
    String getCode();

    /**
     * 设置响应提示信息.
     *
     * @param msg 设置响应提示信息.
     */
    void setMsg(String msg);

    /**
     * 获得响应信息.
     *
     * @return 响应信息
     */
    String getMsg();
}
