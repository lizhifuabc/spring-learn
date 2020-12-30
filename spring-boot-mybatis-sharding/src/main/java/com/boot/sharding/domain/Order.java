package com.boot.sharding.domain;

import lombok.Data;

/**
 * Order
 *
 * @author lizhifu
 * @date 2020/12/29
 */
@Data
public class Order {
    private Long id;
    private Long version;
    private Long userId;
    private Long orderId;
}
