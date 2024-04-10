package com.spring.modulith.department;

/**
 * 部门外部API
 *
 * @author lizhifu
 * @since 2024/4/8
 */
public interface DepartmentExternalAPI {
    /**
     * 根据ID获取部门
     * @param id 部门ID
     * @return 部门
     */
    DepartmentDTO getDepartmentByIdWithUsers(Long id);

    /**
     * 添加部门
     * @param department 部门
     * @return 部门
     */
    DepartmentDTO add(DepartmentDTO department);
}
