package com.boot.jpa.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * User
 *
 * @author lizhifu
 * @date 2021/1/4
 */
@Data
@Entity
@Table(name = "my_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name", nullable = false, length = 36, unique = false)
    private String userName;
}
