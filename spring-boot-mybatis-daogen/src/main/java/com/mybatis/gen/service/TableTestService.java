package com.mybatis.gen.service;

import java.util.Map;
import java.util.List;
import com.mybatis.gen.domain.TableTest;
/**
* 测试表
* @author lizhifu
* @date 2021-03-18
*/
public interface TableTestService {
    /**
    * 新增
    * @author lizhifu
    * @date 2021/03/18
    **/
    int insert(TableTest tableTest);

    /**
    * 根据主键刪除
    * @author lizhifu
    * @date 2021/03/18
    **/
    int deleteByPrimaryKey(Long primaryKey);

    /**
    * 根据主键更新
    * @author lizhifu
    * @date 2021/03/18
    **/
    int updateByPrimaryKey(TableTest tableTest);

    /**
    * 根据主键查询
    * @author lizhifu
    * @date 2021/03/18
    **/
    TableTest selectByPrimaryKey(Long primaryKey);

    /**
    * 通用查询
    * @author lizhifu
    * @date 2021/03/18
    **/
    List<TableTest> selectList(TableTest tableTest);

    /**
    * 根据唯一建[info3]查询
    * @author lizhifu
    * @date 2021/03/18
    **/
    TableTest selectByInfo3(String info3);
    /**
    * 根据唯一建[info4]查询
    * @author lizhifu
    * @date 2021/03/18
    **/
    TableTest selectByInfo4(String info4);
        /**
        * 根据唯一约束[info1]查询
        * @author lizhifu
        * @date 2021/03/18
        **/
        TableTest selectByInfo1(TableTest tableTest);
}