package com.boot.pk.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * sequence实现
 * <p>mybatis可以借助MySQLMaxValueIncrementer实现<p/>
 *
 * @author lizhifu
 * @date 2020/12/16
 */
@RestController
public class MysqlSequence {
    @Resource
    private JdbcTemplate jdbcTemplate;
    /**本地缓存数量*/
    private int cacheSize = 10000;
    /** 下一个ID */
    private long nextId = 0;
    /** 获取last_insert_id */
    private static final String VALUE_SQL = "select last_insert_id()";
    /** 最大ID，开始去数据库获取新的ID，进行缓存到本地 */
    private long maxId = 0;
    @GetMapping("/getNextKey")
    private synchronized long getNextKey(){
        if (this.maxId == this.nextId) {
            String sql = "update my_db.id_gen set order_no = last_insert_id(order_no+"+cacheSize+")";
            jdbcTemplate.update(sql);
            Integer id = jdbcTemplate.queryForObject(VALUE_SQL,Integer.class);
            this.maxId = id.longValue();
            this.nextId = this.maxId - cacheSize + 1;
        }else {
            this.nextId++;
        }
        return nextId;
    }
}
