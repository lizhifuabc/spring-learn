package com.spring.boot.testcontainer.mapper;

import com.spring.boot.testcontainer.TestDemoApplication;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * MyInfo
 *
 * @author lizhifu
 * @since 2023/7/23
 */
@SpringBootTest(classes = TestDemoApplication.class)
public class MyInfoMapperTest {
    @Resource
    private MyInfoMapper myInfoMapper;
    @Test
    public void test(){
        myInfoMapper.insert("MyInfoMapperTestMyInfoMapperTest");
    }
}
