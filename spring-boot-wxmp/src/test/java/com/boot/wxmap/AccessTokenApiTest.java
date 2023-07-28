package com.boot.wxmap;

import com.boot.wxmap.api.AccessTokenApi;
import com.boot.wxmap.api.accesstoken.AccessTokenParam;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * AccessTokenApi
 *
 * @author lizhifu
 * @date 2021/1/26
 */
@SpringBootTest
public class AccessTokenApiTest {
    @Test
    public void getAccessToken(){
        AccessTokenParam accessTokenParam = AccessTokenParam.builder()
                .appId("wx7a75dd18c16e65ae")
                .secret("demo")
                .build();
        AccessTokenApi api = new AccessTokenApi();
        api.getAccessToken(accessTokenParam);
    }
}
