package com.boot.saas.mapper;

import com.boot.saas.domain.MyInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

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
    @Insert("insert into my_info values(#{myInfo.infoId},#{myInfo.userName})")
    public void insert(@Param("myInfo") MyInfo myInfo);
}
