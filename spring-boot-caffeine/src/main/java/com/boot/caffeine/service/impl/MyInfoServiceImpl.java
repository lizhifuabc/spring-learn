package com.boot.caffeine.service.impl;

import com.boot.caffeine.domain.MyInfo;
import com.boot.caffeine.mapper.MyInfoMapper;
import com.boot.caffeine.service.MyInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * MyInfoServiceImpl
 *
 * @author lizhifu
 * @date 2020/12/25
 */
@Service
@Slf4j
public class MyInfoServiceImpl implements MyInfoService {
    @Resource
    private MyInfoMapper myInfoMapper;

    @Override
    @CachePut(value = "myInfo", key = "#myInfo.infoId")
    public MyInfo insert(MyInfo myInfo) {
        myInfoMapper.insert(myInfo);
        log.info("为id、key为:" + myInfo.getInfoId() + "数据做了缓存");
        return myInfo;
    }

    /**
     * sync：设置如果缓存过期是不是只放一个请求去请求数据库，其他请求阻塞，默认是false。
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = "myInfo", key = "#id", sync = true)
    public MyInfo selectById(Integer id) {
        log.info("开始执行数据库查询{}",id);
        MyInfo myInfo = myInfoMapper.selectById(id);
        return myInfo;
    }
}
