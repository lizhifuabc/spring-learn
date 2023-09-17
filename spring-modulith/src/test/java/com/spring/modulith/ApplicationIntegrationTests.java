package com.spring.modulith;

import com.spring.modulith.order.Order;
import com.spring.modulith.order.OrderManagement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO
 *
 * @author lizhifu
 * @since 2023/9/16
 */
@SpringBootTest
public class ApplicationIntegrationTests {
    @Autowired
    OrderManagement orders;
    @Test
    void completesOrder() throws Exception {

        orders.complete(new Order());

        // Thread.sleep(2000);
    }
}
