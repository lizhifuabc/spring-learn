package com.mybatis.gen;

import com.mybatis.gen.domain.GenTableColumn;
import com.mybatis.gen.domain.GenTableUnique;
import com.mybatis.gen.mapper.GenTableMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * GenTableMapper
 *
 * @author lizhifu
 * @date 2021/3/18
 */
@SpringBootTest
public class GenTableMapperBaseTest {
    @Resource
    GenTableMapper genTableMapper;
    @Test
    public void test(){
        String tableName = "t_table_test";
        List<GenTableUnique> list = genTableMapper.selectUniqueTableByName(tableName);
        list.forEach(res->{
            System.out.println(res.getConstraintName()+":"+res.getGenTableColumnUniques().toString());
        });
        List<GenTableColumn> columns = genTableMapper.selectDbTableColumnsByName(tableName);
        columns.forEach(res->{
            System.out.println(res.toString());
        });
    }
}
