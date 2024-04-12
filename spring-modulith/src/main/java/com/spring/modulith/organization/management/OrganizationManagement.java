package com.spring.modulith.organization.management;

import com.spring.modulith.organization.OrganizationDTO;
import com.spring.modulith.organization.OrganizationExternalAPI;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author lizhifu
 * @since 2024/4/12
 */
@Service
public class OrganizationManagement implements OrganizationExternalAPI {
    @Override
    public OrganizationDTO findByIdWithEmployees(Long id) {
        return null;
    }

    @Override
    public OrganizationDTO findByIdWithDepartments(Long id) {
        return null;
    }

    @Override
    public OrganizationDTO findByIdWithDepartmentsAndEmployees(Long id) {
        return null;
    }

    @Override
    public OrganizationDTO add(OrganizationDTO organization) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
