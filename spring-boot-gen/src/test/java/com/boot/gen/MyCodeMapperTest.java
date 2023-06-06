package com.boot.gen;

import com.boot.gen.mycode.domain.MyCode;
import com.boot.gen.mycode.mapper.MyCodeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * MyCodeMapper测试
 *
 * @author lizhifu
 * @date 2020/12/8
 */
@SpringBootTest
public class MyCodeMapperTest {
    @Resource
    MyCodeMapper myCodeMapper;
    @Test
    public void test(){
        MyCode myCode = new MyCode();
        myCode.setCreateTime(LocalDateTime.now());
        myCode.setAmount(new BigDecimal(100));
        myCode.setCode("2");
        myCodeMapper.insertMyCode(myCode);
    }
}
