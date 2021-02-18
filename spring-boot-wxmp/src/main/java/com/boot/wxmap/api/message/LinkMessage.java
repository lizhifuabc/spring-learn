package com.boot.wxmap.api.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

/**
 * ；链接消息
 *
 * @author lizhifu
 * @date 2021/2/18
 */
@Data
@XStreamAlias("xml")
public class LinkMessage extends OutMessage {
    /**
     * 消息标题
     */
    @XStreamAlias("Title")
    private String title;

    /**
     * 消息描述
     */
    @XStreamAlias("Description")
    private String description;

    /**
     * 消息链接
     */
    @XStreamAlias("Url")
    private String url;
    @Builder
    private LinkMessage(String toUserName,String fromUserName,String title,String description,String url){
        super(toUserName,fromUserName,MsgType.LINK.getName());
        this.url = url;
        this.title = title;
        this.description = description;
    }
}
