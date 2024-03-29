package com.mybatis.provider;

import com.mybatis.provider.demo.domain.MyInfo;
import com.mybatis.provider.demo.mapper.MyInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;
import java.util.Random;

/**
 * MyInfoMapper
 *
 * @author lizhifu
 * @date 2021/1/6
 */
@SpringBootTest
public class MyInfoMapperTest {
    @Resource
    private MyInfoMapper myInfoMapper;
    @Test
    public void test(){
        Random random = new Random();
        MyInfo myInfo = new MyInfo();
        myInfo.setUserName("小明测试"+random.nextInt());
        System.out.println(myInfoMapper.insert(myInfo));
        System.out.println(myInfo.getKeyProperty());
    }
}
