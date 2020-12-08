package com.boot.gen;

import com.boot.gen.mycode.service.MyCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * MyCodeService测试
 *
 * @author lizhifu
 * @date 2020/12/8
 */
@SpringBootTest
public class MyCodeServiceTest {
    @Resource
    MyCodeService myCodeService;
    @Test
    public void test(){
        System.out.println(myCodeService.selectMyCodeById(100L).toString());
    }
}
