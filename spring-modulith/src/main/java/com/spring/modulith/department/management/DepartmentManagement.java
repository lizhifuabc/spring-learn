package com.spring.modulith.department.management;

import com.spring.modulith.event.OrganizationAddEvent;
import com.spring.modulith.event.OrganizationRemoveEvent;
import com.spring.modulith.department.DepartmentDTO;
import com.spring.modulith.department.DepartmentExternalAPI;
import com.spring.modulith.department.DepartmentInternalAPI;
import com.spring.modulith.department.mapper.DepartmentMapper;
import com.spring.modulith.department.repository.DepartmentRepository;
import com.spring.modulith.user.UserDTO;
import com.spring.modulith.user.UserInternalAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门 Management 层
 *
 * @author lizhifu
 * @since 2024/4/8
 */
@Service
@Slf4j
public class DepartmentManagement implements DepartmentInternalAPI, DepartmentExternalAPI {

    private final DepartmentRepository repository;
    private final UserInternalAPI userInternalAPI;
    private final DepartmentMapper mapper;

    public DepartmentManagement(DepartmentRepository repository,
                                UserInternalAPI userInternalAPI,
                                DepartmentMapper mapper) {
        this.repository = repository;
        this.userInternalAPI = userInternalAPI;
        this.mapper = mapper;
    }

    @Override
    public DepartmentDTO getDepartmentByIdWithUsers(Long id) {
        DepartmentDTO d = repository.findDTOById(id);
        List<UserDTO> dtos = userInternalAPI.getUserByDepartmentId(id);
        d.users().addAll(dtos);
        return d;
    }

    @ApplicationModuleListener
    void onNewOrganizationEvent(OrganizationAddEvent event) {
        log.info("onNewOrganizationEvent(orgId={})", event.getId());
        add(new DepartmentDTO(null, event.getId(), "HR"));
        add(new DepartmentDTO(null, event.getId(), "Management"));
    }

    @ApplicationModuleListener
    void onRemovedOrganizationEvent(OrganizationRemoveEvent event) {
        log.info("onRemovedOrganizationEvent(orgId={})", event.getId());
        repository.deleteByOrganizationId(event.getId());
    }

    @Override
    public DepartmentDTO add(DepartmentDTO department) {
        return mapper.departmentToDepartmentDTO(
                repository.save(mapper.departmentDTOToDepartment(department))
        );
    }

    @Override
    public List<DepartmentDTO> getDepartmentsByOrganizationId(Long id) {
        return repository.findByOrganizationId(id);
    }

    @Override
    public List<DepartmentDTO> getDepartmentsByOrganizationIdWithUsers(Long id) {
        List<DepartmentDTO> departments = repository.findByOrganizationId(id);
        for (DepartmentDTO dep: departments) {
            dep.users().addAll(userInternalAPI.getUserByDepartmentId(dep.id()));
        }
        return departments;
    }
}