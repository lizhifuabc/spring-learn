package com.boot.sharding.domain;

import lombok.Data;

/**
 * Order
 *
 * @author lizhifu
 * @since  2020/12/29
 */
@Data
public class Order {
    private Integer id;
    private Integer version;
    private Integer userId;
    private Integer orderId;
}
