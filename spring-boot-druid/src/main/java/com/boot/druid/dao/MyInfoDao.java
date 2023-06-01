package com.boot.druid.dao;

import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


/**
 * MyInfoDao
 *
 * @author lizhifu
 * @date 2021/4/7
 */
@Service
public class MyInfoDao {
    @Resource
    private JdbcTemplate oneJdbcTemplate;
    @Resource
    private JdbcTemplate twoJdbcTemplate;
    public void select(){
        twoJdbcTemplate.execute("INSERT INTO my_info (user_name) VALUES ('我的财务室');");
        oneJdbcTemplate.execute("INSERT INTO my_info (user_name) VALUES ('我的财务室');");
    }
}
