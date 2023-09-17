package com.spring.modulith.order;

import com.spring.modulith.order.internal.OrderInternal;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单管理
 *
 * @author lizhifu
 * @since 2023/9/16
 */
@Service
@RequiredArgsConstructor
public class OrderManagement {
    private final @NonNull ApplicationEventPublisher events;
    private final @NonNull OrderInternal orderInternal;

    /**
     * 完成订单
     * @param order 订单
     */
    @Transactional(rollbackFor = Exception.class)
    public void complete(Order order) {
        events.publishEvent(new OrderCompleted(order.getId()));
    }
}
