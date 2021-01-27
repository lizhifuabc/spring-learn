package com.boot.wxmap.api;

import com.boot.wxmap.base.BaseAbstractApi;
import com.boot.wxmap.base.WxResponseEntity;
import com.boot.wxmap.client.WxClient;
import com.boot.wxmap.api.oauth.OathTokenParam;
import com.boot.wxmap.api.oauth.OauthParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
/**
 * 微信oath2授权
 * <br>https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/Wechat_webpage_authorization.html
 * @author lizhifu
 * @date 2021/1/25
 */
@Slf4j
public class OauthApi extends BaseAbstractApi {
    /**
     * 请求地址
     */
    private static final String OAUTH2_URI = "https://open.weixin.qq.com/connect/oauth2/authorize";
    /**
     * 获取要进行微信oauth2授权的跳转URL（微信服务器）
     * <br>第一步：用户同意授权，获取code
     * @return
     */
    public String getOAuthUrl(OauthParam oauthParam){
        StringBuffer result = new StringBuffer(OAUTH2_URI+"?");
        result.append("appid="+oauthParam.getAppId());
        result.append("&redirect_uri="+oauthParam.getRedirectUri());
        result.append("&response_type=code");
        result.append("&scope="+oauthParam.getScope().toString());
        if(StringUtils.isEmpty(oauthParam.getState())){
            result.append("&state="+oauthParam.getState());
        }
        result.append("#wechat_redirect");
        log.info("OAuthTools 获取的跳转链接为{}",result.toString());
        return result.toString();
    }
    /**
     * oauth微信授权流程中获取的access_token
     * <br>第二步：通过code换取网页授权access_token
     * @return
     */
    public WxResponseEntity getAccessToken(OathTokenParam oathTokenParam){
        WxResponseEntity wxResponseEntity = new WxResponseEntity();
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+
                oathTokenParam.getAppId()+"&secret="+oathTokenParam.getAppSecret()+"&code="+oathTokenParam.getCode()+"&grant_type=authorization_code";
        WxClient.create()
                .withUri(url,"")
                .withFunction(this::defaultGet)
                .withConsumer(responseEntity -> {
                    wxResponseEntity.setBody(responseEntity.getBody());
                    log.info("getAccessToken 返回值为{}",responseEntity.getBody());
                }).execute();
        return wxResponseEntity;
    }
}
