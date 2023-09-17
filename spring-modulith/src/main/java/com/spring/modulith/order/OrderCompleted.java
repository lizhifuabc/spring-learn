package com.spring.modulith.order;

import org.jmolecules.event.types.DomainEvent;

/**
 * 订单完成事件
 *
 * @author lizhifu
 * @since 2023/9/16
 */
public record OrderCompleted(OrderIdentifier orderId) implements DomainEvent {
}
