package com.boot.payment.builder;

import com.boot.payment.enumeration.WechatPayV3Type;
import com.boot.payment.sign.WxSignProvide;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.PrivateKey;
import java.util.Collections;
import java.util.Objects;

/**
 * 微信支付client
 *
 * @author lizhifu
 * @date 2021/1/12
 */
public class WechatPayClientBuilder extends HttpClientBuilder {
    static final String os = System.getProperty("os.name") + "/" + System.getProperty("os.version");
    static final String version = System.getProperty("java.version");
    private WechatPayClientBuilder() {
        super();
        String userAgent = String.format(
                "WechatPay-Apache-HttpClient/%s (%s) Java/%s",
                getClass().getPackage().getImplementationVersion(),
                os,
                version == null ? "Unknown" : version);
        setUserAgent(userAgent);
    }
    public static WechatPayClientBuilder create() {
        return new WechatPayClientBuilder();
    }
    public WechatPayClientBuilder withMerchant(String merchantId, String serialNo, PrivateKey privateKey) {

        return this;
    }
}
