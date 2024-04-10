package com.spring.modulith.department;

import java.util.List;

/**
 * 部门内部API
 *
 * @author lizhifu
 * @since 2024/4/8
 */
public interface DepartmentInternalAPI {
    /**
     * 根据组织ID获取部门
     * @param id 组织ID
     * @return 部门
     */
    List<DepartmentDTO> getDepartmentsByOrganizationId(Long id);
    /**
     * 根据组织ID获取部门和用户
     * @param id 组织ID
     * @return 部门和用户
     */
    List<DepartmentDTO> getDepartmentsByOrganizationIdWithUsers(Long id);
}
