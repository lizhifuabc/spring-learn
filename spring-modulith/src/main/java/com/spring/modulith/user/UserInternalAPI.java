package com.spring.modulith.user;

import java.util.List;

/**
 * 用户内部API
 *
 * @author lizhifu
 * @since 2024/4/8
 */
public interface UserInternalAPI {

    /**
     * 根据部门id查询
     * @param id 部门id
     * @return 用户列表
     */
    List<UserDTO> getUsersByDepartmentId(Long id);
    /**
     * 根据组织id查询
     * @param id 组织id
     * @return 用户列表
     */
    List<UserDTO> getUsersByOrganizationId(Long id);

}