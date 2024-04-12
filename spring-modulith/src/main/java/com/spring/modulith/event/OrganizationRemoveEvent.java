package com.spring.modulith.event;

import lombok.Getter;
import lombok.Setter;

/**
 * 组织删除事件
 *
 * @author lizhifu
 * @since 2024/4/8
 */
@Setter
@Getter
public class OrganizationRemoveEvent {
    private Long id;

    public OrganizationRemoveEvent(Long id) {
        this.id = id;
    }

}
