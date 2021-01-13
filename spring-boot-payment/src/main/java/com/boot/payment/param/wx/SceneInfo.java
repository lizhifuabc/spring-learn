package com.boot.payment.param.wx;

import lombok.Data;

/**
 * 支付场景描述
 * @author lizhifu
 */
@Data
public class SceneInfo {
    /**
     * 用户的客户端IP，支持IPv4和IPv6两种格式的IP地址。
     * 示例值：14.23.150.211
     */
    private String payerClientIp;
    /**
     * 商户端设备号（门店号或收银设备ID）。
     * 示例值：013467007045764
     */
    private String deviceId;
    /**
     * 商户门店信息
     */
    private StoreInfo storeInfo;
    /**
     * H5 场景信息
     */
    private H5Info h5Info;
}
