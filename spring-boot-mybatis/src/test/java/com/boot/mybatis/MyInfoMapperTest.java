package com.boot.mybatis;

import com.boot.mybatis.domain.MyInfo;
import com.boot.mybatis.mapper.MyInfoMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

/**
 * MyInfoMapperTest
 *
 * @author lizhifu
 * @since  2020/12/11
 */
@SpringBootTest
public class MyInfoMapperTest {
    @Resource
    MyInfoMapper myInfoMapper;
    @Test
    public void test(){
        MyInfo myInfo = new MyInfo();
        myInfo.setInfoId(new Random().nextInt());
        myInfo.setUserName("test");
        myInfoMapper.insert(myInfo,1);
        MyInfo res = myInfoMapper.selectById(1000);
    }
}
