package com.mybatis.example.controller;

import com.mybatis.example.domain.MyInfo;
import com.mybatis.example.mapper.MyInfoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.Random;

/**
 * IndexController
 *
 * @author lizhifu
 * @date 2020/12/15
 */
@RestController
public class IndexController {
    @Resource
    private MyInfoMapper myInfoMapper;
    @GetMapping("/")
    public Integer index(){
        MyInfo myInfo = new MyInfo();
        myInfo.setInfoId(new Random().nextInt());
        myInfo.setUserName("test");
        int i = myInfoMapper.insert(myInfo);
        return i;
    }
}
