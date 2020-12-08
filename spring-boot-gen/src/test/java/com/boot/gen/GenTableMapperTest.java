package com.boot.gen;

import com.boot.gen.mapper.GenTableMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * GenTableMapper 测试
 *
 * @author lizhifu
 * @date 2020/12/8
 */
@SpringBootTest
public class GenTableMapperTest {
    @Resource
    GenTableMapper genTableMapper;
    @Test
    public void test(){
        System.out.println(genTableMapper.selectTableByName("my_code").toString());
    }
}
