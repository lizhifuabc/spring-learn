package com.boot.wxmap.api.message;

import com.boot.wxmap.util.XmlDataConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Builder;
import lombok.Data;

/**
 * 文本消息
 *
 * @author lizhifu
 * @date 2021/1/27
 */
@Data
@XStreamAlias("xml")
public class TextMessage extends OutMessage{
    /**
     * 回复内容
     */
    @XStreamAlias("Content")
    @XStreamConverter(value = XmlDataConverter.class)
    private String content;
    @Builder
    private TextMessage(String content,String toUserName,String fromUserName){
        super(toUserName,fromUserName,MsgType.TEXT.getName());
        this.content = content;
    }
}
