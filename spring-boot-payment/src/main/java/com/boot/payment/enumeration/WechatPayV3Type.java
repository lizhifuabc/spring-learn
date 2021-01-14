package com.boot.payment.enumeration;

import org.springframework.http.HttpMethod;

/**
 * 微信V3地址
 * https://pay.weixin.qq.com/wiki/doc/apiv3/index.shtml
 * @author lizhifu
 * @date 2021/1/12
 */
public enum WechatPayV3Type {
    /**
     * 统一下单API
     * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_1.shtml
     */
    JSAPI(HttpMethod.POST, "%s/v3/pay/transactions/jsapi"),
    /**
     * 营销图片上传API
     */
    MARKETING_IMAGE_UPLOAD(HttpMethod.POST, "%s/v3/marketing/favor/media/image-upload");
    /**
     * HttpMethod:方法类型
     */
    private final HttpMethod method;
    /**
     * 地址pattern
     */
    private final String pattern;

    /**
     * 构造函数
     * @param method HttpMethod方法
     * @param pattern 地址pattern
     */
    WechatPayV3Type(HttpMethod method, String pattern) {
        this.method = method;
        this.pattern = pattern;
    }
    public HttpMethod method() {
        return this.method;
    }
    public String pattern() {
        return this.pattern;
    }
}
