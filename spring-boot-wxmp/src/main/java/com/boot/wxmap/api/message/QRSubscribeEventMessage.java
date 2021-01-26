package com.boot.wxmap.api.message;

/**
 * 扫描带参数二维码事件 之  1. 用户未关注时，进行关注后的事件推送
 * <xml>
 *   <ToUserName><![CDATA[toUser]]></ToUserName>
 *   <FromUserName><![CDATA[FromUser]]></FromUserName>
 *   <CreateTime>123456789</CreateTime>
 *   <MsgType><![CDATA[event]]></MsgType>
 *   <Event><![CDATA[subscribe]]></Event>
 *   <EventKey><![CDATA[qrscene_123123]]></EventKey>
 *   <Ticket><![CDATA[TICKET]]></Ticket>
 * </xml>
 * @author lizhifu
 * @date 2021/1/26
 */
public class QRSubscribeEventMessage {
}
