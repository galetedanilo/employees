package com.devsuperior.employees.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.employees.dto.DepartmentDTO;
import com.devsuperior.employees.entities.Department;
import com.devsuperior.employees.mappers.DepartmentMapper;
import com.devsuperior.employees.repositories.DepartmentRepository;
import com.devsuperior.employees.responses.DepartmentResponse;
import com.devsuperior.employees.services.exceptions.DatabaseException;
import com.devsuperior.employees.services.exceptions.ResourceNotFoundException;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class DepartmentService {

	
	private final DepartmentRepository repository;
	
	private final DepartmentMapper mapper = DepartmentMapper.INSTANCE;
	
	private void copyDtoToEntity(DepartmentDTO dto, Department entity) {
		
		entity.setName(dto.getName());
	}
	
	@Transactional(readOnly = true)
	public Page<DepartmentResponse> findAllDepartments(Pageable pageable) {
		
		Page<Department> page = repository.findAll(pageable);
		
		return page.map(entity -> mapper.departmentToDepartmentResponse(entity));
	}
	
	@Transactional(readOnly = true)
	public DepartmentDTO findById(Long id) {
		
		Optional<Department> optional = repository.findById(id);
		
		Department entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return new DepartmentDTO(entity);
	}
	
	@Transactional
	public DepartmentDTO insert(DepartmentDTO dto) {
		
		Department entity = new Department();
		
		copyDtoToEntity(dto, entity);
		
		entity = repository.save(entity);
		
		return new DepartmentDTO(entity);
	}
	
	@Transactional
	public DepartmentDTO update(Long id, DepartmentDTO dto) {
		
		try {
			Department entity = repository.getById(id);
			
			copyDtoToEntity(dto, entity);

			entity = repository.save(entity);
			
			return new DepartmentDTO(entity);
			
		} catch(EntityNotFoundException ex) {
			throw new ResourceNotFoundException("Entity not found " + id);
		}
	}
	
	public void delete(Long id) {
		
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("Entity not found " + id);
		} catch(DataIntegrityViolationException ex) {
			throw new DatabaseException("Integrity violation");
		}
	}
}
