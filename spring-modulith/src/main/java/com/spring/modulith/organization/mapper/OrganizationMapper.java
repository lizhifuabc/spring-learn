package com.spring.modulith.organization.mapper;

import org.mapstruct.MappingConstants;

/**
 * 组织
 *
 * @author lizhifu
 * @since 2024/4/8
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrganizationMapper {
    OrganizationDTO organizationToOrganizationDTO(Organization organization);
    Organization organizationDTOToOrganization(OrganizationDTO organizationDTO);
}
