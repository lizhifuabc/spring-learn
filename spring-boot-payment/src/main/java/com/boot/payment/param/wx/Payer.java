package com.boot.payment.param.wx;

import lombok.Data;

/**
 * 支付者信息
 * @author lizhifu
 */
@Data
public class Payer {
    /**
     * 用户在直连商户appid下的唯一标识。
     * 示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     */
    private String openid;
    /**
     * 用户服务标识
     */
    private String spOpenid;
    /**
     * 用户子标识
     */
    private String subOpenid;
}
