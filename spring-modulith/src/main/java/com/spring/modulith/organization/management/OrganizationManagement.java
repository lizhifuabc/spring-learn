package com.spring.modulith.organization.management;

import com.spring.modulith.department.DepartmentDTO;
import com.spring.modulith.department.DepartmentInternalAPI;
import com.spring.modulith.event.OrganizationAddEvent;
import com.spring.modulith.event.OrganizationRemoveEvent;
import com.spring.modulith.organization.OrganizationDTO;
import com.spring.modulith.organization.OrganizationExternalAPI;
import com.spring.modulith.organization.mapper.OrganizationMapper;
import com.spring.modulith.organization.repository.OrganizationRepository;
import com.spring.modulith.user.UserDTO;
import com.spring.modulith.user.UserExternalAPI;
import com.spring.modulith.user.UserInternalAPI;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 组织 Management 层
 *
 * @author lizhifu
 * @since 2024/4/12
 */
@Service
public class OrganizationManagement implements OrganizationExternalAPI {
    private final ApplicationEventPublisher events;
    private final OrganizationRepository repository;
    private final DepartmentInternalAPI departmentInternalAPI;
    private final UserInternalAPI userInternalAPI;
    private final OrganizationMapper mapper;

    public OrganizationManagement(ApplicationEventPublisher events, OrganizationRepository repository, DepartmentInternalAPI departmentInternalAPI, UserInternalAPI userInternalAPI, OrganizationMapper mapper) {
        this.events = events;
        this.repository = repository;
        this.departmentInternalAPI = departmentInternalAPI;
        this.userInternalAPI = userInternalAPI;
        this.mapper = mapper;
    }

    @Override
    public OrganizationDTO findByIdWithUsers(Long id) {
        OrganizationDTO dto = repository.findDTOById(id);
        List<UserDTO> dtos = userInternalAPI.getUsersByOrganizationId(id);
        dto.users().addAll(dtos);
        return dto;
    }

    @Override
    public OrganizationDTO findByIdWithDepartments(Long id) {
        OrganizationDTO dto = repository.findDTOById(id);
        List<DepartmentDTO> dtos = departmentInternalAPI.getDepartmentsByOrganizationId(id);
        dto.departments().addAll(dtos);
        return dto;
    }

    @Override
    public OrganizationDTO findByIdWithDepartmentsAndEmployees(Long id) {
        OrganizationDTO dto = repository.findDTOById(id);
        List<DepartmentDTO> dtos = departmentInternalAPI.getDepartmentsByOrganizationIdWithUsers(id);
        dto.departments().addAll(dtos);
        return dto;
    }

    @Override
    @Transactional
    public OrganizationDTO add(OrganizationDTO organization) {
        OrganizationDTO dto = mapper.organizationToOrganizationDTO(
                repository.save(mapper.organizationDTOToOrganization(organization))
        );
        events.publishEvent(new OrganizationAddEvent(dto.id()));
        return dto;
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
        events.publishEvent(new OrganizationRemoveEvent(id));
    }
}
