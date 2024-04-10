package com.spring.modulith.department.repository;

import com.spring.modulith.department.DepartmentDTO;
import com.spring.modulith.department.model.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 部门Repository
 *
 * @author lizhifu
 * @since 2024/4/8
 */
public interface DepartmentRepository extends CrudRepository<Department, Long> {

    /**
     * 根据ID查询部门
     * @param id 部门ID
     * @return 部门
     */
    @Query("""
           SELECT new pl.piomin.services.department.DepartmentDTO(d.id, d.organizationId, d.name)
           FROM Department d
           WHERE d.id = :id
           """)
    DepartmentDTO findDTOById(Long id);

    /**
     * 根据组织ID查询部门
     * @param organizationId 组织ID
     * @return 部门
     */
    @Query("""
           SELECT new pl.piomin.services.department.DepartmentDTO(d.id, d.organizationId, d.name)
           FROM Department d
           WHERE d.organizationId = :organizationId
           """)
    List<DepartmentDTO> findByOrganizationId(Long organizationId);

    /**
     * 根据组织ID删除部门
     * @param organizationId 组织ID
     */
    void deleteByOrganizationId(Long organizationId);
}