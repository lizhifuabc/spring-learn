package com.spring.modulith;

/**
 * 组织添加事件
 *
 * @author lizhifu
 * @since 2024/4/8
 */
public class OrganizationAddEvent {
    private Long id;

    public OrganizationAddEvent(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
