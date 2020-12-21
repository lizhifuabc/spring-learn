package com.boot.hot.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 账户
 *
 * @author lizhifu
 * @date 2020/12/18
 */
@Data
public class Account {
    private Long id;
    private Long version;
    private Long accountNo;
    private BigDecimal balance;
    private LocalDateTime createTime;
}
