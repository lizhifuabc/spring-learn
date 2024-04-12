package com.spring.modulith.user.management;

import com.spring.modulith.event.OrganizationRemoveEvent;
import com.spring.modulith.user.UserDTO;
import com.spring.modulith.user.UserExternalAPI;
import com.spring.modulith.user.UserInternalAPI;
import com.spring.modulith.user.mapper.UserMapper;
import com.spring.modulith.user.model.User;
import com.spring.modulith.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户 Management 层
 *
 * @author lizhifu
 * @since 2024/4/8
 */
@Service
@Slf4j
public class UserManagement implements UserInternalAPI, UserExternalAPI {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserManagement(UserRepository repository,
                          UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<UserDTO> getUsersByDepartmentId(Long departmentId) {
        return repository.findByDepartmentId(departmentId);
    }

    @Override
    public List<UserDTO> getUsersByOrganizationId(Long id) {
        return repository.findByOrganizationId(id);
    }

    @Override
    @Transactional
    public UserDTO add(UserDTO userDTO) {
        User user = mapper.userDTOTouser(userDTO);
        return mapper.userEntityToUserDTO(repository.save(user));
    }

    @ApplicationModuleListener
    void onRemovedOrganizationEvent(OrganizationRemoveEvent event) {
        log.info("onRemovedOrganizationEvent(orgId={})", event.getId());
        repository.deleteByOrganizationId(event.getId());
    }
}