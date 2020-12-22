package com.boot.saas;

import com.boot.saas.mapper.MysqlAccountMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * MysqlAccount
 *
 * @author lizhifu
 * @date 2020/12/22
 */
@SpringBootTest
public class MysqlAccountTest {
    @Resource
    MysqlAccountMapper mysqlAccountMapper;
    @Test
    public void test(){
        mysqlAccountMapper.selectByTenant("test").toString();
    }
}
