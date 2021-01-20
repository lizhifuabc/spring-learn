package com.boot.payment.sign;

import lombok.SneakyThrows;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.Base64Utils;
import org.springframework.util.IdGenerator;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 微信签名
 * https://pay.weixin.qq.com/wiki/doc/apiv3/wechatpay/wechatpay4_0.shtml
 * @author lizhifu
 * @date 2021/1/12
 */
public class WxSignProvide {
    /**
     * 随机字符串生成
     */
    private static final IdGenerator idGenerator = new AlternativeJdkIdGenerator();
    /**
     * Authorization: 认证类型
     */
    private static final String AUTHORIZATION = "WECHATPAY2-SHA256-RSA2048 ";
    /**
     * token 格式
     * Authorization: WECHATPAY2-SHA256-RSA2048 mchid="1900009191",nonce_str="593BEC0C930B
     */
    public static final String TOKEN_PATTERN = "mchid=\"%s\",nonce_str=\"%s\",timestamp=\"%d\",serial_no=\"%s\",signature=\"%s\"";
    /**
     * V3  SHA256withRSA 签名.
     *
     * @param method       请求方法  GET  POST PUT DELETE 等
     * @param canonicalUrl 例如  https://api.mch.weixin.qq.com/v3/pay/transactions/app?version=1 ——> /v3/pay/transactions/app?version=1
     * @param timestamp    当前时间戳   因为要配置到TOKEN 中所以 签名中的要跟TOKEN 保持一致
     * @param nonceStr     随机字符串  要和TOKEN中的保持一致
     * @param body         请求体 GET 为 "" POST 为JSON
     * @param privateKey      商户API 证书解析的密钥对  实际使用的是其中的私钥
     * @return the string
     */
    @SneakyThrows
    public static String sign(String method, String canonicalUrl, long timestamp, String nonceStr, String body, PrivateKey privateKey)  {
        return sign(privateKey,method,canonicalUrl,String.valueOf(timestamp),nonceStr, body);
    }
    @SneakyThrows
    public static String sign(PrivateKey privateKey, String... orderedComponents) {
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(privateKey);
        String signatureStr = Arrays.stream(orderedComponents)
                .collect(Collectors.joining("\n", "", "\n"));
        signer.update(signatureStr.getBytes(StandardCharsets.UTF_8));
        return Base64Utils.encodeToString(signer.sign());
    }
    /**
     * 生成token
     * @param method
     * @param canonicalUrl
     * @param body
     * @param serialNo
     * @param mchId
     * @param privateKey
     * @return
     */
    public static String authorization(String method, String canonicalUrl,String body,String serialNo,String mchId, PrivateKey privateKey){
        long timestamp = System.currentTimeMillis() / 1000;
        String nonceStr = idGenerator.generateId()
                .toString()
                .replaceAll("-", "");
        String sign = sign(method,canonicalUrl,timestamp,nonceStr,body,privateKey);
        // 生成token
        String token = String.format(TOKEN_PATTERN,mchId, nonceStr, timestamp, serialNo, sign);
        return AUTHORIZATION.concat(token);
    }
}
