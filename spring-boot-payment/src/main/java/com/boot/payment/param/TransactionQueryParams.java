package com.boot.payment.param;

import lombok.Data;

/**
 * 微信支付订单号查询API请求参数.
 *
 * @author felord.cn
 * @since 1.0.0.RELEASE
 */
@Data
public class TransactionQueryParams{
    /**
     * appId
     */
    private String appId;
    /**
     * 直连商户号
     */
    private String mchId;
    /**
     * 商户订单号
     */
    private String transactionIdOrOutTradeNo;
}
