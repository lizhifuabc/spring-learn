package com.boot.druid.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * MyInfoDao
 *
 * @author lizhifu
 * @date 2021/4/7
 */
@Service
public class MyInfoDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    public void select(){
        jdbcTemplate.execute("INSERT INTO my_db.my_info (user_name) VALUES ('我的财务室');");
    }
}
