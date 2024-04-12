package com.spring.modulith.user.mapper;

import com.spring.modulith.user.UserDTO;
import com.spring.modulith.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * UserMapper 映射
 *
 * @author lizhifu
 * @since 2024/4/8
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    /**
     * UserEntity转UserDTO
     * @param user UserEntity
     * @return UserDTO
     */
    UserDTO userEntityToUserDTO(User user);

    /**
     * UserDTO转UserEntity
     * @param userDTO UserDTO
     * @return UserEntity
     */
    User userDTOTouser(UserDTO userDTO);
}
