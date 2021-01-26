package com.boot.wxmap.api.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 扫描带参数二维码事件 之  2. 用户已关注时的事件推送
 * <xml>
 *   <ToUserName><![CDATA[toUser]]></ToUserName>
 *   <FromUserName><![CDATA[FromUser]]></FromUserName>
 *   <CreateTime>123456789</CreateTime>
 *   <MsgType><![CDATA[event]]></MsgType>
 *   <Event><![CDATA[SCAN]]></Event>
 *   <EventKey><![CDATA[SCENE_VALUE]]></EventKey>
 *   <Ticket><![CDATA[TICKET]]></Ticket>
 * </xml>
 * @author lizhifu
 * @date 2021/1/26
 */
@Data
public class QRScanEventMessage {
    /**
     * 开发者微信号
     */
    @XStreamAlias("ToUserName")
    protected String toUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    @XStreamAlias("FromUserName")
    protected String fromUserName;
    /**
     * 消息创建时间
     */
    @XStreamAlias("CreateTime")
    protected long createTime;
    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    @XStreamAlias("Ticket")
    private String ticket;
    /**
     * 消息类型，event
     */
    @XStreamAlias("MsgType")
    protected MsgType msgType;
    /**
     * 事件类型，SCAN
     */
    @XStreamAlias("Event")
    protected Event event;
    /**
     * 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
     */
    @XStreamAlias("EventKey")
    protected String eventKey;
    /**
     * 消息id，64位整型
     */
    @XStreamAlias("MsgId")
    protected String msgId;
}
