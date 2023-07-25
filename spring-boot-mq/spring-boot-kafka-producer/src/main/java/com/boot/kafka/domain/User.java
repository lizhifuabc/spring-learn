package com.boot.kafka.domain;

import lombok.Data;

/**
 * User
 *
 * @author lizhifu
 * @since 2023/7/25
 */
@Data
public class User {
    private Integer id;
    private String name;
    public User(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
