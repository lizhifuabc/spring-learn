package com.boot.caffeine.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * MyInfo实体
 *
 * @author lizhifu
 * @date 2020/12/11
 */
@Data
public class MyInfo implements Serializable {
    /**
     * ID
     */
    private Integer infoId;
    /**
     * 名称
     */
    private String userName;
}
