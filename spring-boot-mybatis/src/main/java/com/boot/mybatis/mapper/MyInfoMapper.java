package com.boot.mybatis.mapper;

import com.boot.mybatis.domain.MyInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * MyInfoMapper
 *
 * @author lizhifu
 * @date 2020/12/11
 */
public interface MyInfoMapper {
    /**
     * 插入
     * @param myInfo
     */
    public void insert(MyInfo myInfo, @Param("userName2")Integer userName);

    /**
     * 查询
     * @param id
     * @return
     */
    @Select("select * from my_info where info_id = #{id}")
    MyInfo selectById(@Param("id") Integer id);
}
