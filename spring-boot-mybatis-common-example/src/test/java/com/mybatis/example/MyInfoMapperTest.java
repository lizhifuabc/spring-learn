package com.mybatis.example;

import com.mybatis.example.domain.MyInfo;
import com.mybatis.example.mapper.MyInfoMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Random;

/**
 * MyInfoMapperTest
 *
 * @author lizhifu
 * @date 2020/12/11
 */
@SpringBootTest
public class MyInfoMapperTest {
    @Resource
    private SqlSessionFactory sqlSessionFactory;
    @Resource
    MyInfoMapper myInfoMapper;
    @Test
    public void test(){
        sqlSessionFactory.getConfiguration();
        MyInfo myInfo = new MyInfo();
        myInfo.setInfoId(new Random().nextInt());
        myInfo.setUserName("test");
        myInfoMapper.insert(myInfo);
        MyInfo res = myInfoMapper.selectById(1000);
        System.out.println(res.getInfoId());
    }
}
