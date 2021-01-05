package com.boot.saas.service.impl;

import com.boot.saas.domain.MyInfo;
import com.boot.saas.mapper.MyInfo2Mapper;
import com.boot.saas.mapper.MyInfoMapper;
import com.boot.saas.service.MysqlAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * MysqlAccountService
 *
 * @author lizhifu
 * @date 2021/1/5
 */
@Service
public class MysqlAccountServiceImpl implements MysqlAccountService {
    @Resource
    private MyInfoMapper myInfoMapper;
    @Resource
    private MyInfo2Mapper myInfo2Mapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void trans() {
        MyInfo myInfo = new MyInfo();
        myInfo.setInfoId(1);
        myInfo.setUserName("lizhifu测试");
        myInfoMapper.insert(myInfo);
        myInfo2Mapper.insert(myInfo);
    }
}
