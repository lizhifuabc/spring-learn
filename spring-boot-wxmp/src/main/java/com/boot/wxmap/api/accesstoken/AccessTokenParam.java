package com.boot.wxmap.api.accesstoken;

import lombok.Builder;
import lombok.Data;

/**
 * AccessTokenParam
 *
 * @author lizhifu
 * @date 2021/1/26
 */
@Data
@Builder
public class AccessTokenParam {
    /**
     * 获取access_token填写client_credential
     */
    private String grantType = "client_credential";
    /**
     * 第三方用户唯一凭证
     */
    private String appId;
    /**
     * 第三方用户唯一凭证密钥，即appsecret
     */
    private String secret;
}
