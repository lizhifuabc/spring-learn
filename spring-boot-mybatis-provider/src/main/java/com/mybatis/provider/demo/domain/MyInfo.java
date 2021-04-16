package com.mybatis.provider.demo.domain;

import com.mybatis.provider.core.BaseEntity;
import com.mybatis.provider.core.annotation.PrimaryKey;
import com.mybatis.provider.core.annotation.Table;
import lombok.Data;

/**
 * MyInfo实体
 *
 * @author lizhifu
 * @date 2020/12/11
 */
@Table("my_info")
@Data
public class MyInfo extends BaseEntity{
    /**
     * ID
     */
    @PrimaryKey
    private Integer infoId;
    /**
     * 名称
     */
    private String userName;
}
