package com.spring.modulith.user;

/**
 * 用户外部API
 *
 * @author lizhifu
 * @since 2024/4/8
 */
public interface UserExternalAPI {
    /**
     * 添加用户
     * @param userDTO 用户
     * @return 用户
     */
    UserDTO add(UserDTO userDTO);
}
