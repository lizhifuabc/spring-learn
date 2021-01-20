package com.boot.payment.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付基础参数：可以进行初始化
 *
 * @author lizhifu
 * @date 2021/1/12
 */
@Data
@Component
@PropertySource("classpath:wechat.properties")
@ConfigurationProperties(prefix = "wx")
public class WxProperties {
    /**
     * 配置文件数据
     */
    private Map<String, Detail> data = new HashMap();
    @Data
    public static class Detail{
        /**
         * appSecret
         */
        private String appSecret;
        /**
         * appId
         */
        private String appId;
        /**
         * 证书路径
         */
        private String certPath;
        /**
         * 证书编号
         */
        private String serialNo;
        /**
         * 商户号
         */
        private String mchId;
    }
}
