package com.boot.sharding.mapper;

import com.boot.sharding.annotation.ShardingStrategy;
import com.boot.sharding.domain.Order;
import com.boot.sharding.strategy.impl.DateTableShardingStrategy;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;

/**
 * OrderTimeMapper
 *
 * @author lizhifu
 * @date 2020/12/30
 */
@ShardingStrategy(logicTable = "t_order",shardingKey = "create_date",tableShardingStrategy = DateTableShardingStrategy.class)
public interface OrderTimeMapper {
    /**
     * 查询
     * @param orderId
     * @return
     */
    @Select("select * from t_order where order_id = #{orderId}")
    Order selectByOrderId(@Param("orderId") Long orderId);

    @Select("select * from t_order where user_id = #{order.userId} and order_id = #{order.orderId}")
    Order select(@Param("order") Order order);

    @Select("select * from t_order where order_id = #{orderId} and user_id = #{userId}")
    Order selectByOrderIdAndUserId(@Param("orderId") Long orderId,@Param("userId")Long userId);

    @Select("select * from t_order where create_date >= #{createDate}")
    Order selectByTime(@Param("createDate") LocalDate createDate);
}
