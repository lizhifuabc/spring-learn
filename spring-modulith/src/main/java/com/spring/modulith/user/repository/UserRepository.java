package com.spring.modulith.user.repository;

import com.spring.modulith.user.UserDTO;
import com.spring.modulith.user.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 用户仓库
 *
 * @author lizhifu
 * @since 2024/4/8
 */
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * 根据部门id查询
     * @param departmentId 部门id
     * @return 用户列表
     */
    List<UserDTO> findByDepartmentId(Long departmentId);
    /**
     * 根据组织id查询
     * @param organizationId 组织id
     * @return 用户列表
     */
    List<UserDTO> findByOrganizationId(Long organizationId);
    /**
     * 根据组织id删除
     * @param organizationId 组织id
     */
    void deleteByOrganizationId(Long organizationId);
}