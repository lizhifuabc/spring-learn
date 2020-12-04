package com.learn.redis.domain;

import java.io.Serializable;

/**
 * 人
 *
 * @author lizhifu
 * @date 2020/12/4
 */
public class Person implements Serializable {
    /**
     * 姓名
     */
    private String name;
    /**
     * id
     */
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
