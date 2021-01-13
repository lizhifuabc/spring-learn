package com.boot.payment.param.wx;

import lombok.Data;

/**
 * 商户门店信息
 * @author lizhifu
 */
@Data
public class StoreInfo {
    /**
     * 商户侧门店编号
     * 示例值：0001
     */
    private String id;
    /**
     * 商户侧门店名称
     * 示例值：腾讯大厦分店
     */
    private String name;
    /**
     * 地区编码，详细请见省市区编号对照表。
     * 示例值：440305
     */
    private String areaCode;
    /**
     * 详细的商户门店地址
     * 示例值：广东省深圳市南山区科技中一道10000号
     */
    private String address;
}
