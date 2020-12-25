package com.boot.caffeine.mapper;

import com.boot.caffeine.domain.MyInfo;
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
    public void insert(@Param("myInfo") MyInfo myInfo);

    /**
     * 查询
     * @param id
     * @return
     */
    @Select("select * from my_info where info_id = #{id}")
    MyInfo selectById(@Param("id") Integer id);
}
