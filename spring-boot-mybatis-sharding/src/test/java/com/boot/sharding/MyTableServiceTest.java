package com.boot.sharding;

import com.boot.sharding.config.DynamicDataSourceContextHolder;
import com.boot.sharding.domain.Order;
import com.boot.sharding.service.MyTableService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;

/**
 * MyTableService
 *
 * @author lizhifu
 * @date 2021/1/5
 */
@SpringBootTest
public class MyTableServiceTest {
    @Resource
    private MyTableService myTableService;

    @Test
    public void test(){
        Order order = new Order();
        order.setUserId(1L);
        order.setOrderId(2L);
//        myTableService.trans(order,1L);
        myTableService.trans(3L,2L);
    }
}
