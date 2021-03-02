package com.mybatis.gen.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.mybatis.gen.domain.Admin;

/**
 * 管理员信息
 * @author lizhifu
 * @date 2021-03-02
 */
public interface AdminMapper {

    /**
    * 新增
    * @author lizhifu
    * @date 2021/03/02
    **/
    Admin insert(Admin admin);

    /**
    * 根据主键刪除
    * @author lizhifu
    * @date 2021/03/02
    **/
    int deleteByPrimaryKey(Long primaryKey);

    /**
    * 根据主键更新
    * @author lizhifu
    * @date 2021/03/02
    **/
    int updateByPrimaryKey(Admin admin);

    /**
    * 根据主键查询
    * @author lizhifu
    * @date 2021/03/02
    **/
    Admin selectByPrimaryKey(Long primaryKey);

    /**
    * 通用查询
    * @author lizhifu
    * @date 2021/03/02
    **/
    List<Admin> selectList(Admin admin);

}
