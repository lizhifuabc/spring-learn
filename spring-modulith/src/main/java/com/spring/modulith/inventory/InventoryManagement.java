package com.spring.modulith.inventory;

import com.spring.modulith.inventory.inventory.InventoryInternal;
import com.spring.modulith.order.OrderCompleted;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.ApplicationModuleListener;
import org.springframework.stereotype.Service;

/**
 * 库存管理
 *
 * @author lizhifu
 * @since 2023/9/16
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryManagement {
    private final InventoryInternal inventoryInternal;
    @ApplicationModuleListener()
    void on(OrderCompleted event) throws InterruptedException {

        var orderId = event.orderId();

        log.info("接收到订单完成事件 {}.", orderId);

        // 模拟库存处理
        Thread.sleep(1000);

        log.info("处理完成订单完成事件 {}.", orderId);
    }
}
