package com.spring.modulith.user;

/**
 * 用户DTO
 *
 * @author lizhifu
 * @since 2024/4/8
 */
public record UserDTO(Long id, Long organizationId, Long departmentId, String name, int age, String position) {

}
