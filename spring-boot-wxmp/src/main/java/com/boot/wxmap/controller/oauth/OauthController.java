package com.boot.wxmap.controller.oauth;

import com.boot.wxmap.base.WxResponseEntity;
import com.boot.wxmap.api.oauth.OathTokenParam;
import com.boot.wxmap.api.oauth.OauthParam;
import com.boot.wxmap.api.OauthApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * OauthController
 * http://testyfyf.s1.natapp.cc/oath?appId=wx7a75dd18c16e65ae&scope=SNSAPI_BASE&redirectUri=http://testyfyf.s1.natapp.cc/getAccessToken
 * @author lizhifu
 * @date 2021/1/25
 */
@Controller
@Slf4j
public class OauthController {
    @GetMapping("/oath")
    public String oath (OauthParam oauthParam){
        OauthApi tools = new OauthApi();
        String oathUrl = tools.getOAuthUrl(oauthParam);
        return "redirect:"+oathUrl;
    }
    @GetMapping("/getAccessToken")
    @ResponseBody
    public String getAccessToken (String code,String state){
        log.info("微信授权返回信息为code:{} state:{}",code,state);
        OauthApi oauthApi = new OauthApi();
        OathTokenParam oathTokenParam = OathTokenParam.builder()
                .appSecret("demo")
                .code(code)
                .appId("wx7a75dd18c16e65ae").build();
        WxResponseEntity wxResponseEntity = oauthApi.getAccessToken(oathTokenParam);
        return wxResponseEntity.getBody().toString();
    }
}
