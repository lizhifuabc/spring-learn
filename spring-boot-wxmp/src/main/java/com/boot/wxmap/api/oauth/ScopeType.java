package com.boot.wxmap.api.oauth;

/**
 * 授权类型
 *
 * @author lizhifu
 * @date 2021/1/25
 */
public enum ScopeType {
    /**
     * 以snsapi_base为scope发起的网页授权，是用来获取进入页面的用户的openid的，
     * 并且是静默授权并自动跳转到回调页的。用户感知的就是直接进入了回调页（往往是业务页面）
     */
    SNSAPI_BASE("snsapi_base"),
    /**
     * 以snsapi_userinfo为scope发起的网页授权，
     * 是用来获取用户的基本信息的。但这种授权需要用户手动同意，并且由于用户同意过，
     * 所以无须关注，就可在授权后获取该用户的基本信息。
     */
    SNSAPI_USERINFO("snsapi_userinfo");

    private String scope;

    ScopeType(String scope){
        this.scope=scope;
    }

    @Override
    public String toString() {
        return this.scope;
    }
}
