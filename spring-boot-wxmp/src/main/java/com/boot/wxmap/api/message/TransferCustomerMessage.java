package com.boot.wxmap.api.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 消息转发到客服
 * https://developers.weixin.qq.com/doc/offiaccount/Customer_Service/Forwarding_of_messages_to_service_center.html
 *
 * @author lizhifu
 * @date 2021/1/27
 */
@Data
@XStreamAlias("xml")
public class TransferCustomerMessage extends OutMessage {
    public TransferCustomerMessage(String toUserName, String fromUserName) {
        super(toUserName, fromUserName, MsgType.TRANSFER_CUSTOMER_SERVICE.getName());
    }
}
