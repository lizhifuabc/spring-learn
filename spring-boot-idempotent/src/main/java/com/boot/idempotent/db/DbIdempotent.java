package com.boot.idempotent.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 基于数据库保证幂等性：方法非常简单，无效引入第三方组件
 * <p>创建一只有两个字段的数据库表<p/>
 * <p>其中将第三方流水号作为唯一性索引<p/>
 * <p>数据库设置规则定期删除，释放表空间<p/>
 * <p>流程：A\B --> db表-->通过再执行相关业务<p/>
 * @author lizhifu
 * @date 2020/12/15
 */
@RestController
public class DbIdempotent {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @GetMapping("/db")
    public String db (String thirdNo){
        String sql = "insert into my_db.db_idempotent(third_no) values(?)";
        jdbcTemplate.update(sql,thirdNo);
        return "success";
    }
}
