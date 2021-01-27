package com.boot.wxmap.api.message;

import com.boot.wxmap.util.XmlDataConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.AllArgsConstructor;

/**
 * 回复消息
 *
 * @author lizhifu
 * @date 2021/1/27
 */
public class OutMessage {
    @XStreamAlias("ToUserName")
    @XStreamConverter(value = XmlDataConverter.class)
    private String toUserName;

    @XStreamAlias("FromUserName")
    @XStreamConverter(value = XmlDataConverter.class)
    private String fromUserName;

    @XStreamAlias("CreateTime")
    private Long createTime;

    @XStreamAlias("MsgType")
    @XStreamConverter(value = XmlDataConverter.class)
    private String msgType;
    private OutMessage(){}
    public OutMessage(String toUserName,String fromUserName,String msgType){
        this.createTime = System.currentTimeMillis() / 1000L;
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.msgType = msgType;
    }
}
