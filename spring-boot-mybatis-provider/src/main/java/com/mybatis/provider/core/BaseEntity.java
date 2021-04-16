package com.mybatis.provider.core;

import com.mybatis.provider.core.annotation.NoColumn;
import lombok.Data;

/**
 * 基础实体bean
 *
 * @author lizhifu
 * @date 2021/1/6
 */
@Data
public class BaseEntity {
    /**
     * 插入时将主键数据返回给此字段值
     * 主键不一定是ID，所以将值统一返回给keyProperty
     */
    @NoColumn
    private Integer keyProperty;
}
