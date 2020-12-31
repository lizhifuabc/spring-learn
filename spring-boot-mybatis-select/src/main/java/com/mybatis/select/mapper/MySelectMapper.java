package com.mybatis.select.mapper;

import com.mybatis.select.domain.MySelect;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * my_select
 *
 * @author lizhifu
 * @date 2020/12/31
 */
public interface MySelectMapper {
    /**
     * count(*)包括了所有的列，相当于行数，在统计结果的时候，不会忽略列值为NULL
     * count(1)包括了忽略所有列，用1代表代码行，在统计结果的时候，不会忽略列值为NULL
     * count(列名)只包括列名那一列，在统计结果的时候，会忽略列值为空（null）的计数
     * 由于count(*)在mysql中单独做了优化，所以直接使用count(*)往往是最优的选项
     * 举例说明：如果表中只有一个主键，那么查询其实没有区别，但是如果有二级的索引，此时Innodb情况下回大大的提高效率；
     * Innodb的主键索引是聚簇索引（包含了KEY，除了KEY之外的其他字段值，事务ID和MVCC回滚指针）
     * 所以主键索引一定会比二级索引（包含KEY和对应的主键ID）大，也就是说在有二级索引的情况下，一般COUNT()都不会通过主键索引来统计行数，在有多个二级索引的情况下选择占用空间最小的。
     * explain select count(*) from my_select;
     * 一个优化方案就是预先建一个小字段并建二级索引专门用来统计行数。
     * @return
     */
    @Select("select count(select_id) from my_select")
    public int count();
    @Insert("insert into my_select (order_no,user_name) values(#{mySelect.orderNo},#{mySelect.userName})")
    public void insert(@Param("mySelect") MySelect mySelect);

    /**
     * 分页起点越大查询速度越慢:
     * select * from my_select order by select_id desc limit 400000,10
     * select * from my_select order by select_id desc limit 0,10
     * 优化(通常会有根据时间来查询的sql,此时优化的方式是一样的)：
     * select * from my_select where id >  (select id from my_select order by id desc  limit 1000000, 1) order by id desc limit 0,10
     * (适合id连续的系统)select * from my_select  where id  between 1000000 and 1000010  order by id desc
     * @return
     */
    @Select("select * from my_select order by select_id desc limit 400000,10")
    public List<MySelect> select();
}
