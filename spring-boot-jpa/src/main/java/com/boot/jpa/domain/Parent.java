package com.boot.jpa.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 父对象
 *
 * @author lizhifu
 * @since 2023/7/24
 */
@Entity
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    /**
     * @OneToMany 注解用于建立一对多关系
     * cascade = CascadeType.ALL 表示在进行保存、更新、删除等操作时，会级联操作关联的对象
     * 不创建实际的外键约束
     */
    @OneToMany
    @JoinColumn(name = "parent_id",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
    private List<Child> children = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }
}
