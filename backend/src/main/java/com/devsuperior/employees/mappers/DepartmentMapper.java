package com.devsuperior.employees.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.devsuperior.employees.entities.Department;
import com.devsuperior.employees.requests.DepartmentRequest;
import com.devsuperior.employees.responses.DepartmentResponse;

@Mapper
public interface DepartmentMapper {

	DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
	
	Department departmentRequestToDepartment(DepartmentRequest request);
	
	DepartmentResponse departmentToDepartmentResponse(Department entity);
}
