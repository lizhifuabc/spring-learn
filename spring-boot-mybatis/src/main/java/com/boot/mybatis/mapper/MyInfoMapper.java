package com.boot.mybatis.mapper;

import com.boot.mybatis.domain.MyInfo;
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
    public void insert(@Param("myInfo")MyInfo myInfo);
}
