package com.boot.caffeine;

import com.boot.caffeine.domain.MyInfo;
import com.boot.caffeine.service.MyInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;
import java.util.Random;

/**
 * MyInfoService
 *
 * @author lizhifu
 * @date 2020/12/25
 */
@SpringBootTest
public class MyInfoServiceTest {
    @Resource
    private MyInfoService myInfoService;
    @Test
    public void test(){
        for (int i = 0; i < 2; i++) {
            Random random = new Random();
            int id = random.nextInt();
            MyInfo myInfo = new MyInfo();
            myInfo.setUserName("小米");
            myInfo.setInfoId(id);
            myInfoService.insert(myInfo);
            MyInfo d = myInfoService.selectById(id);
            System.out.println(d.toString());
        }
    }
}
