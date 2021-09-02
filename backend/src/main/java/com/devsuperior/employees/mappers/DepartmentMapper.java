package com.devsuperior.employees.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.devsuperior.employees.entities.Department;
import com.devsuperior.employees.requests.DepartmentRequest;
import com.devsuperior.employees.responses.DepartmentResponse;

@Mapper
public interface DepartmentMapper {

	DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
	
	@Mapping(target = "employees", ignore = true)
	Department departmentRequestToDepartment(DepartmentRequest request);
	
	@Mapping(source = "id", target = "departmentId")
	DepartmentResponse departmentToDepartmentResponse(Department entity);
}
