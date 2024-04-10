package com.spring.modulith.organization;

/**
 * TODO
 *
 * @author lizhifu
 * @since 2024/4/8
 */
public interface OrganizationExternalAPI {
    OrganizationDTO findByIdWithEmployees(Long id);
    OrganizationDTO findByIdWithDepartments(Long id);
    OrganizationDTO findByIdWithDepartmentsAndEmployees(Long id);
    OrganizationDTO add(OrganizationDTO organization);
    void remove(Long id);
}
