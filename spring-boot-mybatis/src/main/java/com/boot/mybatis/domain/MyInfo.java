package com.boot.mybatis.domain;

import java.io.Serializable;

/**
 * MyInfo实体
 *
 * @author lizhifu
 * @date 2020/12/11
 */
public class MyInfo implements Serializable {
    /**
     * ID
     */
    private Integer infoId;
    /**
     * 名称
     */
    private String userName;

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
