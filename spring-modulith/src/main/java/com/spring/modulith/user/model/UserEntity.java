package com.spring.modulith.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * 员工实体
 *
 * @author lizhifu
 * @since 2024/4/8
 */
@Setter
@Getter
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 组织id
     */
    private Long organizationId;
    /**
     * 部门id
     */
    private Long departmentId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private int age;
    /**
     * 职位
     */
    private String position;
}
