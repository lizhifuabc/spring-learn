package com.boot.wxmap.api;

import com.boot.wxmap.api.accesstoken.AccessTokenParam;
import com.boot.wxmap.base.BaseAbstractApi;
import com.boot.wxmap.base.WxResponseEntity;
import com.boot.wxmap.client.WxClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * AccessToken
 * https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_access_token.html
 * @author lizhifu
 * @date 2021/1/26
 */
@Slf4j
public class AccessTokenApi extends BaseAbstractApi {
    /**
     * 获取access_token
     * @param accessTokenParam
     * @return
     */
    @SneakyThrows
    public WxResponseEntity getAccessToken(AccessTokenParam accessTokenParam){
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+
                accessTokenParam.getAppId()+"&secret="+accessTokenParam.getSecret();
        WxResponseEntity wxResponseEntity = new WxResponseEntity();
        WxClient.create()
                .withUri(url,"")
                .withFunction(this::defaultGet)
                .withConsumer(responseEntity -> {
                    wxResponseEntity.setBody(responseEntity.getBody());
                    log.info("getAccessToken 返回值为{}",responseEntity.getBody());
                }).execute();
        //可进行数据转换
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue(wxResponseEntity.getBody().toString(), HashMap.class);
        return wxResponseEntity;
    }
}
