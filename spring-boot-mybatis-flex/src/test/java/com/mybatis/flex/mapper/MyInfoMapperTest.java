package com.mybatis.flex.mapper;

import com.mybatis.flex.domain.entity.MyInfo;
import com.mybatis.flex.domain.entity.table.MyInfoTableDef;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * MyInfoMapper
 *
 * @author lizhifu
 * @since 2023/7/23
 */
@SpringBootTest
public class MyInfoMapperTest {
    @Resource
    private MyInfoMapper myInfoMapper;

    @Test
    public void test(){
        MyInfo myInfo = new MyInfo();
        myInfo.setUserName("lizhifu");
        System.out.println(myInfoMapper.insert(myInfo));


        QueryWrapper queryWrapper = QueryWrapper.create()
                .select()
                .where(MyInfoTableDef.MY_INFO.USER_NAME.eq("lizhifu"));
        List<MyInfo> myInfo1 = myInfoMapper.selectListByQuery(queryWrapper);
        System.out.println(myInfo1);
    }
}
