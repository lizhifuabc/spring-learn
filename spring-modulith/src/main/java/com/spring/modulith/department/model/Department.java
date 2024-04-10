package com.spring.modulith.department.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * 部门实体
 *
 * @author lizhifu
 * @since 2024/4/8
 */
@Setter
@Getter
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long organizationId;
    private String name;

    public Department() {}

    public Department(Long organizationId, String name) {
        this.organizationId = organizationId;
        this.name = name;
    }

}