package com.boot.saas.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.boot.saas.datasource.DatasourceHolder;
import com.boot.saas.datasource.DynamicDataSource;
import com.boot.saas.domain.MysqlAccount;
import com.boot.saas.mapper.MysqlAccountMapper;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

/**
 * 数据源配置 Controller
 */
@RestController
public class DataSourceAccountController {
    @Resource
    private MysqlAccountMapper mysqlAccountMapper;
    /**
     * 保存
     */
    @GetMapping("/addDataSouce")
    public String addDataSouce(String tenant) {
        MysqlAccount mysqlAccount = mysqlAccountMapper.selectByTenant(tenant);

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(mysqlAccount.getUrl());
        druidDataSource.setUsername(mysqlAccount.getUsername());
        druidDataSource.setPassword(mysqlAccount.getPassword());
        DynamicDataSource.setDataSource("dbkey",druidDataSource);

        MysqlAccount mysqlAccount2 = mysqlAccountMapper.selectByTenant(tenant);
        return mysqlAccount2.toString();
    }
}
