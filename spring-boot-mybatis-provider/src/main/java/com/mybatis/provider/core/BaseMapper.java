package com.mybatis.provider.core;

import com.mybatis.provider.core.provider.*;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.io.Serializable;
import java.util.List;

/**
 * 基础mapper
 *
 * @author lizhifu
 * @date 2021/1/6
 */
public interface BaseMapper<Entity> {
    /**
     * 插入,并返回主键的值{@link BaseEntity} keyProperty属性的值
     * @param entity 实体
     * @return  条数
     */
    @InsertProvider(type = InsertSqlProvider.class, method = "buildSql")
    @Options(useGeneratedKeys = true, keyProperty = TableInfo.DEFAULT_PRIMARY_KEY)
    Integer insert(Entity entity);
    /**
     * 根据主键进行更新，注意更新为全部字段，包括null
     *
     * @param entity  实体
     * @return        条数
     */
    @UpdateProvider(type = UpdateSqlProvider.class, method = "buildSql")
    int updateByPrimaryKey(Entity entity);
    /**
     * 返回总数count(*)
     *
     * @return  总数
     */
    @SelectProvider(type = CountSqlProvider.class, method = "buildSql")
    Long count();

    /**
     * 根据条件查询符合条件的实体总数
     *
     * @param entity  实体
     * @return    数量
     */
    @SelectProvider(type = CountByEntitySqlProvider.class, method = "buildSql")
    Long countByEntity(Entity entity);
    /**
     * 根据主键查询实体
     *
     * @param id  主键值
     * @return    实体
     */
    @SelectProvider(type = SelectByPrimaryKeyInSqlProvider.class, method = "buildSql")
    Entity selectByPrimaryKey(Serializable id);

    /**
     * 根据实体条件查询符合条件的实体list
     * @param entity  条件实体
     * @return          list
     */
    @SelectProvider(type = SelectByEntitySqlProvider.class, method = "buildSql")
    List<Entity> selectByEntity(Entity entity);

    /**
     * 根据条件查询单个数据
     *
     * @param entity  实体条件
     * @return          实体对象
     */
    @SelectProvider(type = SelectByEntitySqlProvider.class, method = "buildSql")
    Entity selectOneByEntity(Entity entity);
}
