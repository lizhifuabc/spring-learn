package com.spring.boot.testcontainer.mapper;


import org.apache.ibatis.annotations.Insert;

/**
 * MyInfo
 *
 * @author lizhifu
 * @since 2023/7/23
 */
public interface MyInfoMapper{
    @Insert("insert into my_info(user_name) values(#{userName})")
    int insert(String userName);
}
