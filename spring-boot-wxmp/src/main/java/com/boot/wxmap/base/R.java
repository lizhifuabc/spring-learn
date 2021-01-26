package com.boot.wxmap.base;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 定义返回结果
 *
 * @author lizhifu
 * @date 2020/12/24
 */
@Data
public class R<T> {
    /**
     * 标识
     */
    private boolean success;

    /**
     * 消息
     */
    private String msg;

    /**
     * 返回码：http状态码或者自定义的异常状态码
     */
    private Integer code;
    /**
     * 结果
     */
    private T result;

    public R success(String msg) {
        this.success = true;
        this.msg = msg;
        this.code = HttpStatus.OK.value();
        return this;
    }
    public R success() {
        this.success = true;
        this.msg = "成功";
        this.code = HttpStatus.OK.value();
        return this;
    }
    public R success(T result) {
        this.success = true;
        this.msg = "成功";
        this.code = HttpStatus.OK.value();
        this.result = result;
        return this;
    }
    public R success(String msg,T result) {
        this.success = true;
        this.msg = msg;
        this.code = HttpStatus.OK.value();
        this.result = result;
        return this;
    }
    public R error(String msg, Integer code) {
        this.success = false;
        this.msg = msg;
        this.code = code;
        return this;
    }
}
