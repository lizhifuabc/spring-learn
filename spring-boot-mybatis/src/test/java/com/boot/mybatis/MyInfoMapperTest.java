package com.boot.mybatis;

import com.boot.mybatis.domain.MyInfo;
import com.boot.mybatis.mapper.MyInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * MyInfoMapperTest
 *
 * @author lizhifu
 * @date 2020/12/11
 */
@SpringBootTest
public class MyInfoMapperTest {
    @Resource
    MyInfoMapper myInfoMapper;
    @Test
    public void test(){
        MyInfo myInfo = new MyInfo();
        myInfo.setInfoId(1000);
        myInfo.setUserName("test");
        myInfoMapper.insert(myInfo);
    }
}
