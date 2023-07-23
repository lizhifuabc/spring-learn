package com.mybatis.flex.domain.entity;

import com.mybatisflex.annotation.Table;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * MyInfo
 *
 * @author lizhifu
 * @since 2023/7/23
 */
@ToString
@Data
@Table("my_info")
public class MyInfo {
    private Long infoId;
    private String userName;
}
