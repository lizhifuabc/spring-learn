package com.doc.demo;

/**
 * User
 *
 * @author lizhifu
 * @date 2021/1/8
 */
public class User {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public boolean equals(Object obj) {
        return (this == obj);
    }
}
