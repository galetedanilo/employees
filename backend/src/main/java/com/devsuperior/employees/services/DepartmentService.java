package com.devsuperior.employees.services;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.employees.entities.Department;
import com.devsuperior.employees.mappers.DepartmentMapper;
import com.devsuperior.employees.repositories.DepartmentRepository;
import com.devsuperior.employees.requests.DepartmentRequest;
import com.devsuperior.employees.responses.DepartmentResponse;
import com.devsuperior.employees.services.exceptions.DatabaseException;
import com.devsuperior.employees.services.exceptions.ResourceNotFoundException;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class DepartmentService {

	
	private final DepartmentRepository departmentRepository;
	
	private final DepartmentMapper departmentMapper = DepartmentMapper.INSTANCE;
	
	@Transactional(readOnly = true)
	public Page<DepartmentResponse> findAllDepartments(Pageable pageable) {
		
		Page<Department> page = departmentRepository.findAll(pageable);
		
		return page.map(departmentEntity -> departmentMapper.departmentToDepartmentResponse(departmentEntity));
	}
	
	@Transactional(readOnly = true)
	public DepartmentResponse findDepartmentById(Long id) {
		
		Optional<Department> optional = departmentRepository.findById(id);
		
		Department departmentEntity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return departmentMapper.departmentToDepartmentResponse(departmentEntity);
	}
	
	@Transactional
	public DepartmentResponse addNewDepartment(DepartmentRequest departmentRequest) {
		
		Department departmentEntity = departmentMapper.departmentRequestToDepartment(departmentRequest);
		
		departmentEntity = departmentRepository.save(departmentEntity);
		
		return departmentMapper.departmentToDepartmentResponse(departmentEntity);
	}
	
	@Transactional
	public DepartmentResponse updateDepartment(Long id, DepartmentRequest departmentRequest) {
		
		verifyIfDepartmentExists(id);
		
		Department departmentEntity = departmentMapper.departmentRequestToDepartment(departmentRequest);
		
		departmentEntity.setId(id);
		
		departmentEntity = departmentRepository.save(departmentEntity);
		
		return departmentMapper.departmentToDepartmentResponse(departmentEntity);
	}

	public void deleteDepartment(Long id) {
		
		try {
			departmentRepository.deleteById(id);
		} catch(EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("Entity not found " + id);
		} catch(DataIntegrityViolationException ex) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	private void verifyIfDepartmentExists(Long id) {
		departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department " + id + " not found"));
	}
}
