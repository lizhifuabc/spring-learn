package com.boot.sharding;

import com.boot.sharding.domain.Order;
import com.boot.sharding.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * OrderMapper
 *
 * @author lizhifu
 * @date 2020/12/29
 */
@SpringBootTest
public class OrderMapperTest {
    @Resource
    private OrderMapper orderMapper;
    @Test
    public void test(){
        Order param = new Order();
        param.setOrderId(1L);
        param.setUserId(1L);
        Order order = orderMapper.selectByOrderIdAndUserId(1L,1L);
        System.out.println(order.toString());
    }
}
