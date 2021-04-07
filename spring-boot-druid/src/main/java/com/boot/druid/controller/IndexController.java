package com.boot.druid.controller;

import com.boot.druid.dao.MyInfoDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * IndexController
 *
 * @author lizhifu
 * @date 2020/12/4
 */
@RestController
public class IndexController {
    @Resource
    private MyInfoDao myInfoDao;
    @GetMapping("/")
    public String helloWorld(){
        myInfoDao.select();
        return "helloWorld";
    }
}
