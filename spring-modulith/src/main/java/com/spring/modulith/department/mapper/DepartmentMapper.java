package com.spring.modulith.department.mapper;

import com.spring.modulith.department.DepartmentDTO;
import com.spring.modulith.department.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * 部门Mapper
 *
 * @author lizhifu
 * @since 2024/4/8
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DepartmentMapper {
    /**
     * Department转DepartmentDTO
     * @param department 部门
     * @return DepartmentDTO
     */
    DepartmentDTO departmentToDepartmentDTO(Department department);
    /**
     * DepartmentDTO转Department
     * @param departmentDTO 部门
     * @return Department
     */
    Department departmentDTOToDepartment(DepartmentDTO departmentDTO);
}
