package com.boot.payment.param.wx;

import lombok.Data;

/**
 * 订单金额信息
 * @author lizhifu
 */
@Data
public class Amount {
    /**
     * 订单总金额，单位为分
     */
    private int total;
    /**
     * CNY：人民币，境内商户号仅支持人民币。
     */
    private String currency = "CNY";
}
