package com.mybatis.common.base;

import com.mybatis.common.annotation.MapperXmlElement;
import com.mybatis.common.constant.BaseConstant;
import com.mybatis.common.enums.XmlTag;
import org.apache.ibatis.annotations.Param;

/**
 * 插入:<insert></insert>
 * <p>PK:主键；ET：实体<p/>
 * @author lizhifu
 * @date 2020/12/14
 */
@MapperXmlElement(id = "insert",xmlTag = XmlTag.INSERT,include = "BaseMybatisTemplate.insert")
public interface BaseInsertMapper<ET> extends ParentMapper{
    /**
     * 实体插入
     * @param et 实体
     * @return 插入返回主键值
     */
    int insert(@Param(BaseConstant.PARAM_ENTITY) ET et);
}