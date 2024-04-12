package com.spring.modulith.event;

import lombok.Getter;
import lombok.Setter;

/**
 * 组织添加事件
 *
 * @author lizhifu
 * @since 2024/4/8
 */
@Setter
@Getter
public class OrganizationAddEvent {
    private Long id;

    public OrganizationAddEvent(Long id) {
        this.id = id;
    }

}
