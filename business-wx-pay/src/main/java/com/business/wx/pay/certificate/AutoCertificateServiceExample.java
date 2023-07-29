package com.business.wx.pay.certificate;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.RSAConfig;
import com.wechat.pay.java.core.certificate.AutoCertificateService;
import com.wechat.pay.java.service.certificate.CertificateService;

import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * 证书下载
 *
 * @author lizhifu
 * @since 2023/7/28
 */
public class AutoCertificateServiceExample {
    public static String merchantId = "merchantId";
    public static String privateKeyPath = "privateKeyPath";
    public static String merchantSerialNumber = "privateKeyPath";
    public static String wechatPayCertificatePath = "privateKeyPath";
    public static String apiV3key = "privateKeyPath";
    public static AutoCertificateService autoCertificateService;

    public static void main(String[] args) {
        Config config =
                new RSAAutoCertificateConfig.Builder()
                        .merchantId(merchantId)
                        .privateKeyFromPath(privateKeyPath)
                        .merchantSerialNumber(merchantSerialNumber)
                        .apiV3Key(apiV3key)
                        .build();
    }
}
