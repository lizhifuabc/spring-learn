package com.boot.wxmap.api.oauth;

import lombok.Builder;
import lombok.Data;

/**
 * oauth微信授权流程中获取的access_token
 *
 * @author lizhifu
 * @date 2021/1/26
 */
@Data
@Builder
public class OathTokenParam {
    /**
     * code
     */
    private String code;
    /**
     * appId
     */
    private String appId;
    /**
     * appSecret
     */
    private String appSecret;
}
