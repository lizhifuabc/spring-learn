package com.spring.modulith.order;

import lombok.Getter;
import org.jmolecules.ddd.types.AggregateRoot;

import java.util.UUID;

/**
 * 订单聚合根
 *
 * @author lizhifu
 * @since 2023/9/16
 */
@Getter
public class Order implements AggregateRoot<Order, OrderIdentifier> {
    private final OrderIdentifier id = new OrderIdentifier(UUID.randomUUID());
}
