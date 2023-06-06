package com.boot.saas.controller;

import com.boot.saas.service.MysqlAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * 测试切换数据源之后的事务是否生效
 * 1 切换数据源接口：/addDataSouce
 * 2 测试本接口
 *
 * @author lizhifu
 * @date 2021/1/5
 */
@RestController
public class TransController {
    @Resource
    private MysqlAccountService mysqlAccountService;
    @GetMapping("/trans")
    public String trans(){
        //测试切换数据源之后的事务是否生效
        mysqlAccountService.trans();
        return "success";
    }
}
