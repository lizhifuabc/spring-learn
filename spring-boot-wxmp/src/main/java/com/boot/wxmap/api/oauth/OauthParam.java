package com.boot.wxmap.api.oauth;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 请求参数
 *
 * @author lizhifu
 * @date 2021/1/25
 */
@Data
@Builder
@AllArgsConstructor
public class OauthParam {
    /**
     * 公众号appId
     */
    @NotNull
    private String appId;
    /**
     * 重定向的回调链接地址(需要使用urlEncode)
     */
    @NotNull
    private String redirectUri;
    /**
     * 应用授权作用域
     * snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid）
     * snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息 ）
     */
    @NotNull
    private ScopeType scope;
    /**
     * 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节，原样返回参数数据
     */
    private String state;
}
