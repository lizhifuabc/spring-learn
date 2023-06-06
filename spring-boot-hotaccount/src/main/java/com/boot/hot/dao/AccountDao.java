package com.boot.hot.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.math.BigDecimal;

/**
 * AccountDao
 * 账号只有两个操作，加(add)或者减(reduce)
 * @author lizhifu
 * @date 2020/12/18
 */
@Repository
public class AccountDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 加款:只保证每次更的金额
     * @param amount
     * @param accountNo
     */
    public int add(BigDecimal amount,Long accountNo){
        String sql = "update my_db.account set balance = balance + ?," +
                "version = version +1 where account_no = ?";
        return jdbcTemplate.update(sql,amount,accountNo);
    }

    /**
     * 扣款：保证每次更的金额+金额不会扣成负
     * @param amount
     * @param accountNo
     * @return
     */
    public int reduce(BigDecimal amount,Long accountNo){
        String sql = "update my_db.account set balance = balance - ?," +
                "version = version +1 where account_no = ? and balance >= ?";
        return jdbcTemplate.update(sql,amount,accountNo,amount);
    }
}
