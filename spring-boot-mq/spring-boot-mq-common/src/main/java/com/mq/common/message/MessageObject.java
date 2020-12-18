package com.mq.common.message;

import java.io.Serializable;

/**
 * 消息
 *
 * @author lizhifu
 * @date 2020/12/17
 */
public class MessageObject implements Serializable {
    /**
     * 消息内容
     */
    private String message;

    public MessageObject(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
