package com.boot.im.domain;

import lombok.Data;

/**
 * 聊天数据
 *
 * @author lizhifu
 * @date 2021/3/19
 */
@Data
public class Msg {
    /** 发送者id **/
    private Long fromUserId;

    /** 接收者id **/
    private Long toUserId;

    /** 消息内容 **/
    private String msg;
    /**
     * 类型 0：文字 1：图片
     */
    private Integer type = 0;
    /**
     * 状态 0：未读 1：已读
     */
    private Integer status = 0 ;
}
