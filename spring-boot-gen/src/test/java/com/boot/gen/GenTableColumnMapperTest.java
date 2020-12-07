package com.boot.gen;

import com.boot.gen.domain.GenTableColumn;
import com.boot.gen.mapper.GenTableColumnMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * GenTableColumnMapper 测试
 *
 * @author lizhifu
 * @date 2020/12/7
 */
@SpringBootTest
public class GenTableColumnMapperTest {
    @Autowired
    GenTableColumnMapper genTableColumnMapper;
    @Test
    public void test(){
        List<GenTableColumn> list = genTableColumnMapper.selectDbTableColumnsByName("my_info");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }
}
