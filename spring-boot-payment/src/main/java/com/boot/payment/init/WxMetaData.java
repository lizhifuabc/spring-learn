package com.boot.payment.init;

import com.boot.payment.properties.WxProperties;
import lombok.Data;

import java.security.PrivateKey;

/**
 * 微信支付元数据
 *
 * @author lizhifu
 * @date 2021/1/12
 */
@Data
public class WxMetaData {
    /**
     * privateKey
     */
    private PrivateKey privateKey;
    /**
     * 配置文件信息
     */
    private WxProperties.Detail detail;
}
