package com.spring.modulith.gateway;

import com.spring.modulith.department.DepartmentDTO;
import com.spring.modulith.department.DepartmentExternalAPI;
import com.spring.modulith.organization.OrganizationDTO;
import com.spring.modulith.organization.OrganizationExternalAPI;
import com.spring.modulith.user.UserDTO;
import com.spring.modulith.user.UserExternalAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 通过REST API公开内部模块
 *
 * @author lizhifu
 * @since 2024/4/12
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class GatewayManagement {
    private final DepartmentExternalAPI departmentExternalAPI;
    private final UserExternalAPI userExternalAPI;
    private final OrganizationExternalAPI organizationExternalAPI;

    public GatewayManagement(DepartmentExternalAPI departmentExternalAPI, UserExternalAPI userExternalAPI, OrganizationExternalAPI organizationExternalAPI) {
        this.departmentExternalAPI = departmentExternalAPI;
        this.userExternalAPI = userExternalAPI;
        this.organizationExternalAPI = organizationExternalAPI;
    }

    @GetMapping("/organizations/{id}/with-departments")
    public OrganizationDTO apiOrganizationWithDepartments(@PathVariable("id") Long id) {
        return organizationExternalAPI.findByIdWithDepartments(id);
    }

    @GetMapping("/organizations/{id}/with-departments-and-users")
    public OrganizationDTO apiOrganizationWithDepartmentsAndEmployees(@PathVariable("id") Long id) {
        return organizationExternalAPI.findByIdWithDepartmentsAndEmployees(id);
    }

    @PostMapping("/organizations")
    public OrganizationDTO apiAddOrganization(@RequestBody OrganizationDTO o) {
        return organizationExternalAPI.add(o);
    }

    @PostMapping("/users")
    public UserDTO apiAddUser(@RequestBody UserDTO userDTO) {
        log.info("add user: {}", userDTO);
        return userExternalAPI.add(userDTO);
    }

    @GetMapping("/departments/{id}/with-users")
    public DepartmentDTO apiDepartmentWithEmployees(@PathVariable("id") Long id) {
        return departmentExternalAPI.getDepartmentByIdWithUsers(id);
    }

    @PostMapping("/departments")
    public DepartmentDTO apiAddDepartment(@RequestBody DepartmentDTO department) {
        return departmentExternalAPI.add(department);
    }
}
