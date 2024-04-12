package com.spring.modulith.organization.mapper;

import com.spring.modulith.organization.OrganizationDTO;
import com.spring.modulith.organization.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * 组织
 *
 * @author lizhifu
 * @since 2024/4/8
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrganizationMapper {
    /**
     * 实体转DTO
     * @param organization 实体
     * @return dto
     */
    OrganizationDTO organizationToOrganizationDTO(Organization organization);

    /**
     * dto 转实体
     * @param organizationDTO dto
     * @return 实体
     */
    Organization organizationDTOToOrganization(OrganizationDTO organizationDTO);
}
