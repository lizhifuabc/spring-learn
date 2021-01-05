package com.boot.sharding.service.impl;

import com.boot.sharding.annotation.DatabaseStrategy;
import com.boot.sharding.config.DynamicDataSourceContextHolder;
import com.boot.sharding.domain.Order;
import com.boot.sharding.mapper.OrderMapper;
import com.boot.sharding.service.MyTableService;
import com.boot.sharding.strategy.impl.LongHashDatabaseShardingStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * MyTableService
 *
 * @author lizhifu
 * @date 2021/1/5
 */
@Service
public class MyTableServiceImpl implements MyTableService {
    @Resource
    private OrderMapper orderMapper;
    @Override
    @DatabaseStrategy(logicSchema = "ds",shardingKey = "userId",databaseShardingStrategy = LongHashDatabaseShardingStrategy.class)
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void trans(Long userId,long orderId) {
        DynamicDataSourceContextHolder.getCurrentDatasource();
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderId(orderId);
        orderMapper.insert(order);
        throw new RuntimeException("回滚测试");
    }

    @Override
    @DatabaseStrategy(logicSchema = "ds",shardingKey = "order.userId",databaseShardingStrategy = LongHashDatabaseShardingStrategy.class)
    public void trans(Order order,long orderId) {
        orderMapper.insert(order);
    }
}
