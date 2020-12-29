package com.boot.sharding.mapper;


import com.boot.sharding.domain.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
/**
 * @author lizhifu
 */
public interface OrderMapper{
    /**
     * 查询
     * @param orderId
     * @return
     */
    @Select("select * from t_order where order_id = #{orderId}")
    Order selectByOrderId(@Param("orderId") Integer orderId);
    @Insert({ "insert into t_order(user_id, order_id) values(#{userId}, #{orderId})" })
    void insert(Order order);
}
