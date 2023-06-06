package com.boot.sharding;

import com.boot.sharding.domain.Order;
import com.boot.sharding.mapper.OrderMapper;
import com.boot.sharding.mapper.OrderTimeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;
import java.time.LocalDate;

/**
 * OrderTimeMapperTest
 *
 * @author lizhifu
 * @date 2020/12/30
 */
@SpringBootTest
public class OrderTimeMapperTest {
    @Resource
    private OrderTimeMapper orderTimeMapper;
    @Test
    public void test(){
        Order param = new Order();
        param.setOrderId(1L);
        param.setUserId(1L);
        Order order = orderTimeMapper.selectByTime(LocalDate.now());
        System.out.println(order.toString());
    }
}
