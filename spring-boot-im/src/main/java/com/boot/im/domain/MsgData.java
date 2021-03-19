package com.boot.im.domain;

import lombok.Data;

/**
 * 聊天数据
 *
 * @author lizhifu
 * @date 2021/3/19
 */
@Data
public class MsgData {
    /**
     * 动作类型
     */
    private Integer action;
    /**
     * 消息内容
     */
    private Msg msg;
}
