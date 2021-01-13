package com.boot.payment.param;

import lombok.Data;

import java.security.KeyPair;

/**
 * 微信支付元数据
 *
 * @author lizhifu
 * @date 2021/1/12
 */
@Data
public class WxMetaData {
    /**
     * keyPair
     */
    private KeyPair keyPair;
    /**
     * 商户API证书serial_no，用于声明所使用的证书
     */
    private String serialNumber;
}
