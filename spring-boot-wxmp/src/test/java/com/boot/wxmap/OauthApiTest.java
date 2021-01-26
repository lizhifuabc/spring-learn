package com.boot.wxmap;

import com.boot.wxmap.api.oauth.OathTokenParam;
import com.boot.wxmap.api.oauth.OauthParam;
import com.boot.wxmap.api.OauthApi;
import com.boot.wxmap.api.oauth.ScopeType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * OauthTools
 *
 * @author lizhifu
 * @date 2021/1/25
 */
@SpringBootTest
public class OauthApiTest {
    @Test
    public void getOAuthUrl(){
        OauthParam oauthParam = OauthParam.builder()
                .appId("wx7a75dd18c16e65ae")
                .scope(ScopeType.SNSAPI_BASE)
                .redirectUri("http://192.168.3.16:8080/learn")
                .build();
        System.out.println(oauthParam.toString());
        OauthApi tools = new OauthApi();
        String url = tools.getOAuthUrl(oauthParam);
        System.out.println(url);
    }
    @Test
    public void getAccessToken(){
        String code = "";
        OauthApi tools = new OauthApi();
        OathTokenParam oathTokenParam = OathTokenParam.builder()
                .appSecret("a20eebe1fa733f85ff66aff09ab0ae0c")
                .code(code)
                .appId("wx7a75dd18c16e65ae").build();
        tools.getAccessToken(oathTokenParam);
    }
}
