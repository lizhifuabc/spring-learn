package com.boot.wxmap.api;

import com.boot.wxmap.api.qrcode.QRCodeParam;
import com.boot.wxmap.base.BaseAbstractApi;
import com.boot.wxmap.base.WxResponseEntity;
import com.boot.wxmap.client.WxClient;
import lombok.extern.slf4j.Slf4j;

/**
 * 生成带参数的二维码
 * https://developers.weixin.qq.com/doc/offiaccount/Account_Management/Generating_a_Parametric_QR_Code.html
 * @author lizhifu
 * @date 2021/1/26
 */
@Slf4j
public class QrcodeApi extends BaseAbstractApi {
    /**
     * 创建二维码ticket
     * @param codeParam 参数
     * @return
     */
    public WxResponseEntity create(QRCodeParam codeParam){
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+codeParam.getAccessToken();
        WxResponseEntity wxResponseEntity = new WxResponseEntity();
        WxClient.create()
                .withUri(url,codeParam)
                .withFunction(this::defaultPost)
                .withConsumer(responseEntity -> {
                    wxResponseEntity.setBody(responseEntity.getBody());
                    log.info("QrcodeApi create 返回值为{}",responseEntity.getBody());
                }).execute();
        return wxResponseEntity;
    }
}
