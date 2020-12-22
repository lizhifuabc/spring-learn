package com.boot.saas.controller;

import com.boot.saas.mapper.MysqlAccountMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * IndexController
 *
 * @author lizhifu
 * @date 2020/12/22
 */
@RestController
public class IndexController {
    @Resource
    private MysqlAccountMapper mysqlAccountMapper;
    @GetMapping("/")
    public String index(){
        return mysqlAccountMapper.selectByTenant("test").toString();
    }
}
