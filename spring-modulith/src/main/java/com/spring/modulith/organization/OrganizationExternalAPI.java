package com.spring.modulith.organization;

/**
 * 组织对外接口
 *
 * @author lizhifu
 * @since 2024/4/8
 */
public interface OrganizationExternalAPI {
    /**
     * 根据id查询
     * @param id id
     * @return dto
     */
    OrganizationDTO findByIdWithUsers(Long id);

    /**
     * 根据id查询
     * @param id  id
     * @return 组织数据
     */
    OrganizationDTO findByIdWithDepartments(Long id);

    /**
     * 根据id查询
     * @param id id
     * @return 组织数据
     */
    OrganizationDTO findByIdWithDepartmentsAndEmployees(Long id);

    /**
     * 添加组织数据
     * @param organization 组织数据
     * @return 组织数据
     */
    OrganizationDTO add(OrganizationDTO organization);

    /**
     * 删除组织数据
     * @param id id
     */
    void remove(Long id);
}
