package com.boot.saas;

import com.boot.saas.datasource.DynamicDataSourceContextHolder;
import com.boot.saas.service.MysqlAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;

/**
 * MysqlAccountService
 *
 * @author lizhifu
 * @date 2021/1/5
 */
@SpringBootTest
public class MysqlAccountServiceTest {
    @Resource
    private MysqlAccountService mysqlAccountService;
    @Test
    public void test(){
        mysqlAccountService.trans();
    }
}
