package com.boot.saas.mapper;

import com.boot.saas.domain.MysqlAccount;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * MysqlAccount
 *
 * @author lizhifu
 * @date 2020/12/22
 */
public interface MysqlAccountMapper {
    /**
     * 查询
     * @param id
     * @return
     */
    @Select("select * from mysql_account where id = #{id}")
    MysqlAccount selectById(@Param("id") Integer id);

    /**
     * 查询
     * @param tenant
     * @return
     */
    @Select("select * from mysql_account where tenant = #{tenant}")
    MysqlAccount selectByTenant(@Param("tenant") String tenant);
}
