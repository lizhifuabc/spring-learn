package com.boot.pk.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 基于数据库的主键自增长
 *  <p>A-->执行非业务表插入操作-->获取到ID-->作为A数据库表的ID</p>
 * @author lizhifu
 * @date 2020/12/16
 */
@RestController
public class DbGen {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @GetMapping("/db")
    public Integer db (){
        String sql = "insert into my_db.id_gen() values()";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }
}
