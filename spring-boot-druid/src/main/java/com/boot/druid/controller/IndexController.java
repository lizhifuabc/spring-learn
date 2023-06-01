package com.boot.druid.controller;

import com.boot.druid.dao.MyInfoDao;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


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
