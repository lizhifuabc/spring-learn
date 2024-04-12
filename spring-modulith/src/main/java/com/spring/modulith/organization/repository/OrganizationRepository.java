package com.spring.modulith.organization.repository;

import com.spring.modulith.organization.OrganizationDTO;
import com.spring.modulith.organization.model.Organization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 组织
 *
 * @author lizhifu
 * @since 2024/4/8
 */
public interface OrganizationRepository extends CrudRepository<Organization, Long> {

    @Query("""
           SELECT new com.spring.modulith.organization.OrganizationDTO(o.id, o.name, o.address)
           FROM Organization o
           WHERE o.id = :id
           """)
    OrganizationDTO findDTOById(Long id);
}