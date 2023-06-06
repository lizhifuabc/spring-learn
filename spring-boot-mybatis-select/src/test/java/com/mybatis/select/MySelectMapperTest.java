package com.mybatis.select;

import com.mybatis.select.domain.MySelect;
import com.mybatis.select.mapper.MySelectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * MySelectMapper
 *
 * @author lizhifu
 * @date 2020/12/31
 */
@SpringBootTest
public class MySelectMapperTest {
    @Resource
    MySelectMapper mySelectMapper;
    @Test
    public void test(){
        LocalDateTime now = LocalDateTime.now();
        int res = mySelectMapper.count();
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(now,end);
        System.out.println(res);
        System.out.println("(相差毫秒数)结束："+ duration.toMillis());
    }
    @Test
    public void select(){
        LocalDateTime now = LocalDateTime.now();
        List<MySelect> res =  mySelectMapper.select();
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(now,end);
        System.out.println(res.size());
        System.out.println("(相差毫秒数)结束："+ duration.toMillis());
    }
    @Test
    public void insert(){
        for (int i = 0; i < 500000; i++) {
            MySelect mySelect = new MySelect();
            mySelect.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
            mySelect.setUserName(UUID.randomUUID().toString());
            mySelectMapper.insert(mySelect);
            System.out.println(i);
        }
    }
}
