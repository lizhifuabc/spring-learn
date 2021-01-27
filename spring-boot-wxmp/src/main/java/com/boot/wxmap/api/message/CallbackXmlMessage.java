package com.boot.wxmap.api.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * 微信消息管理回调message
 *
 * @author lizhifu
 * @date 2021/1/27
 */
public class CallbackXmlMessage {
    @XStreamAlias("ToUserName")
    private String toUserName;

    @XStreamAlias("FromUserName")
    private String fromUserName;

    @XStreamAlias("CreateTime")
    private Long createTime;

    @XStreamAlias("MsgType")
    private String msgType;

    @XStreamAlias("Content")
    private String content;


    @XStreamAlias("MsgId")
    private Long msgId;
}
