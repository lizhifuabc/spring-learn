package com.boot.api.exception;

import lombok.Data;

/**
 * 自定义异常
 *
 * @author lizhifu
 * @date 2020/12/24
 */
@Data
public class CustomException extends RuntimeException{
    /**
     * 返回码
     */
    private int code;
    /**
     * 消息
     */
    private String msg;
    private CustomException(){}
    /**
     *  自定义异常
     * @param code 返回码
     * @param msg 消息
     */
    public CustomException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
