package com.boot.sharding.service;


import com.boot.sharding.domain.Order;

/**
 * 事务测试
 *
 * @author lizhifu
 * @date 2021/1/5
 */
public interface MyTableService {
    public void trans(Long userId,long orderId);
    public void trans(Order order,long orderId);
}
