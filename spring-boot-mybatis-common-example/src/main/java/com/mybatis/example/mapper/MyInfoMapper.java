package com.mybatis.example.mapper;

import com.mybatis.common.base.BaseMapper;
import com.mybatis.example.domain.MyInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * MyInfoMapper
 *
 * @author lizhifu
 * @date 2020/12/11
 */
public interface MyInfoMapper extends BaseMapper<Integer,MyInfo> {
    /**
     * 查询
     * @param id
     * @return
     */
    @Select("select * from my_info where info_id = #{id}")
    MyInfo selectById(@Param("id") Integer id);
}
