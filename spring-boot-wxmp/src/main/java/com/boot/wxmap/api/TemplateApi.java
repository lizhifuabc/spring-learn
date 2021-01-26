package com.boot.wxmap.api;

import com.boot.wxmap.api.template.SendTemplateMessageParam;
import com.boot.wxmap.base.BaseAbstractApi;
import com.boot.wxmap.base.WxResponseEntity;
import com.boot.wxmap.client.WxClient;
import lombok.extern.slf4j.Slf4j;

/**
 * 模板相关api
 * https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Template_Message_Interface.html
 * @author lizhifu
 * @date 2021/1/26
 */
@Slf4j
public class TemplateApi extends BaseAbstractApi {
    /**
     * 发送模板消息
     * @param sendTemplateMessageParam
     */
    public WxResponseEntity sendTemplateMessage(SendTemplateMessageParam sendTemplateMessageParam){
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+sendTemplateMessageParam.getAccessToken();
        WxResponseEntity wxResponseEntity = new WxResponseEntity();
        WxClient.create()
                .withUri(url,sendTemplateMessageParam)
                .withFunction(this::defaultPost)
                .withConsumer(responseEntity -> {
                    wxResponseEntity.setBody(responseEntity.getBody());
                    log.info("sendTemplateMessage 返回值为{}",responseEntity.getBody());
                }).execute();
        return wxResponseEntity;
    }
}
