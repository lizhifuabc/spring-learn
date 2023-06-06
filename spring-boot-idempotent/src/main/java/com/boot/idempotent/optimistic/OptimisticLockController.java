package com.boot.idempotent.optimistic;

import com.boot.idempotent.domain.Optimistic;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * 乐观锁：数据库表增加一个控制字段(version)
 * http://localhost:8080/learn/optimistic?id=1
 * <p>更新时候where version = ?字段<p/>
 * <p>本次更新的字段添加version = version+1，这样数据更新+1<p/>
 * <p>当并发执行的时候，更新的数据只能存在一个<p/>
 * <p>执行顺序：A--->查询数据--->判断状态--->使用查询数据进行更新<p/>
 * @author lizhifu
 * @date 2020/12/15
 */
@RestController
public class OptimisticLockController {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @GetMapping("/optimistic")
    public String optimistic (Integer id){
        String querySql = "select * from my_db.optimistic where id = ?";
        RowMapper<Optimistic> rowMapper=new BeanPropertyRowMapper<Optimistic>(Optimistic.class);
        Optimistic optimistic = jdbcTemplate.queryForObject(querySql,rowMapper,id);
        if(optimistic.getStatus().equals("SUCCESS")){
            throw new RuntimeException("状态错误");
        }
        String sql = "update my_db.optimistic set status = 'SUCCESS',version = version+1 where version = ? and id = ?";
        jdbcTemplate.update(sql,optimistic.getVersion(),id);
        return "success";
    }
}
