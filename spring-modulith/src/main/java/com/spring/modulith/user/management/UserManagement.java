package com.spring.modulith.user.management;

import com.spring.modulith.OrganizationRemoveEvent;
import com.spring.modulith.user.UserDTO;
import com.spring.modulith.user.UserExternalAPI;
import com.spring.modulith.user.UserInternalAPI;
import com.spring.modulith.user.mapper.UserMapper;
import com.spring.modulith.user.model.UserEntity;
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

    private UserRepository repository;
    private UserMapper mapper;

    public UserManagement(UserRepository repository,
                          UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<UserDTO> getUserByDepartmentId(Long departmentId) {
        return repository.findByDepartmentId(departmentId);
    }

    @Override
    public List<UserDTO> getUserByOrganizationId(Long id) {
        return repository.findByOrganizationId(id);
    }

    @Override
    @Transactional
    public UserDTO add(UserDTO userDTO) {
        UserEntity userEntity = mapper.userDTOTouser(userDTO);
        return mapper.userEntityToUserDTO(repository.save(userEntity));
    }

    @ApplicationModuleListener
    void onRemovedOrganizationEvent(OrganizationRemoveEvent event) {
        log.info("onRemovedOrganizationEvent(orgId={})", event.getId());
        repository.deleteByOrganizationId(event.getId());
    }
}