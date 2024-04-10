package com.spring.modulith.department;

import com.spring.modulith.user.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门DTO
 *
 * @author lizhifu
 * @since 2024/4/8
 */
public record DepartmentDTO(Long id,
                            Long organizationId,
                            String name,
                            List<UserDTO> users) {
    public DepartmentDTO(Long id, Long organizationId, String name) {
        this(id, organizationId, name, new ArrayList<>());
    }
}