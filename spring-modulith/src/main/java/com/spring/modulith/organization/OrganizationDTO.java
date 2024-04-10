package com.spring.modulith.organization;

import com.spring.modulith.department.DepartmentDTO;
import com.spring.modulith.user.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 组织DTO
 *
 * @author lizhifu
 * @since 2024/4/8
 */
public record OrganizationDTO(Long id,
                              String name,
                              String address,
                              List<DepartmentDTO> departments,
                              List<UserDTO> users) {
    public OrganizationDTO(Long id, String name, String address) {
        this(id, name, address, new ArrayList<>(), new ArrayList<>());
    }
}