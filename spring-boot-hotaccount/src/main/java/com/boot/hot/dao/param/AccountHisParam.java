package com.boot.hot.dao.param;

import com.boot.hot.enums.DirectionType;
import com.boot.hot.enums.TradeType;
import lombok.Data;

import java.math.BigDecimal;

/**
 * AccountHis参数
 *
 * @author lizhifu
 * @date 2020/12/18
 */
@Data
public class AccountHisParam {
    /**
     * 账号
     */
    private Long accountNo;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 流水号
     */
    private String tradeFlowNo;
    /**
     * 交易类型
     */
    private TradeType tradeType;
    /**
     * 资金变动方向
     */
    private DirectionType direction;
    /**
     * 上游编号
     */
    private Long tradeSignNo;
}
