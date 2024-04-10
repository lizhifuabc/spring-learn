package com.spring.modulith.user.mapper;

import com.spring.modulith.user.UserDTO;
import com.spring.modulith.user.model.UserEntity;
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
     * @param userEntity UserEntity
     * @return UserDTO
     */
    UserDTO userEntityToUserDTO(UserEntity userEntity);

    /**
     * UserDTO转UserEntity
     * @param userDTO UserDTO
     * @return UserEntity
     */
    UserEntity userDTOTouser(UserDTO userDTO);
}
