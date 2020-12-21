package com.boot.hot.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 账户历史
 *
 * @author lizhifu
 * @date 2020/12/18
 */
@Data
public class AccountHis {
    private Long id;
    private Long version;
    private Long accountNo;
    private BigDecimal amount;
    private BigDecimal bBalance;
    private BigDecimal aBalance;
    private String tradeFlowNo;
    private Integer tradeType;
    /**
     * 资金变动方向
     */
    private Integer direction;
    /**
     * 是否入账
     */
    private Integer entry;
    private LocalDateTime createTime;
}
