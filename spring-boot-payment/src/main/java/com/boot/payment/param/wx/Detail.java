package com.boot.payment.param.wx;

import lombok.Data;

import java.util.List;

/**
 * 优惠功能
 * @author lizhifu
 */
@Data
public class Detail {
    /**
     * 订单原价
     * 1、商户侧一张小票订单可能被分多次支付，订单原价用于记录整张小票的交易金额。
     * 2、当订单原价与支付金额不相等，则不享受优惠。
     * 3、该字段主要用于防止同一张小票分多次支付，以享受多次优惠的情况，正常支付订单不必上传此参数。
     * 示例值：608800
     */
    private int costPrice;
    /**
     * 商品小票ID
     * 商家小票ID
     * 示例值：微信123
     */
    private String invoiceId;
    /**
     * 单品列表
     * 单品列表信息
     * 条目个数限制：【1，undefined】
     */
    private List<GoodsDetail> goodsDetail;
}
