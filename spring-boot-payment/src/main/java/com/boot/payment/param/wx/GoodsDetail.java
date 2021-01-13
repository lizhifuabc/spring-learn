package com.boot.payment.param.wx;

import lombok.Data;

/**
 * 单品列表信息
 * 条目个数限制：【1，undefined】
 * @author lizhifu
 */
@Data
public class GoodsDetail {
    /**
     * 商户侧商品编码
     * 由半角的大小写字母、数字、中划线、下划线中的一种或几种组成。
     * 示例值：商品编码
     */
    private String merchantGoodsId;
    /**
     * 微信侧商品编码
     * 微信支付定义的统一商品编号（没有可不传）
     * 示例值：1001
     */
    private String wechatpayGoodsId;
    /**
     * 商品名称
     * 商品的实际名称
     * 示例值：iPhoneX 256G
     */
    private String goodsName;
    /**
     * 商品数量
     * 用户购买的数量
     * 示例值：1
     */
    private int quantity;
    /**
     * 商品单价
     * 商品单价，单位为分
     * 示例值：828800
     */
    private int unitPrice;

}
