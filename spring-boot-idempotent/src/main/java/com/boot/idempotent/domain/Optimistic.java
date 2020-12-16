package com.boot.idempotent.domain;

/**
 * optimistic实体
 *
 * @author lizhifu
 * @date 2020/12/16
 */
public class Optimistic {
    private Integer id;
    private Integer version;
    private String  status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
