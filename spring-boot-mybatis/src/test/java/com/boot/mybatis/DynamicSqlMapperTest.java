package com.boot.mybatis;

import com.boot.mybatis.mapper.DynamicSqlMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * DynamicSqlMapper
 *
 * @author lizhifu
 * @since 2023/7/16
 */
@SpringBootTest
public class DynamicSqlMapperTest {
    @Resource
    private DynamicSqlMapper dynamicSqlMapper;
    @Test
    public void test(){
        dynamicSqlMapper.count();
    }
}
