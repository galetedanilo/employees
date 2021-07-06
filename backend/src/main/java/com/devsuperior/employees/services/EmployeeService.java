package com.devsuperior.employees.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.employees.dto.EmployeeDTO;
import com.devsuperior.employees.entities.Department;
import com.devsuperior.employees.entities.Employee;
import com.devsuperior.employees.repositories.DepartmentRepository;
import com.devsuperior.employees.repositories.EmployeeRepository;
import com.devsuperior.employees.services.exceptions.DatabaseException;
import com.devsuperior.employees.services.exceptions.ResourceNotFoundException;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	private void copyDtoToEntity(EmployeeDTO dto, Employee entity) {
		
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
		
		Department department = departmentRepository.getById(dto.getDepartmentId());
		
		entity.setDepartment(department);
	}
	
	@Transactional(readOnly = true)
	public Page<EmployeeDTO> findAll(Pageable pageable) {
		
		Page<Employee> page = repository.findAll(pageable);
		
		return page.map(entity -> new EmployeeDTO(entity));
	}
	
	@Transactional(readOnly = true)
	public EmployeeDTO findById(Long id) {
		
		Optional<Employee> optional = repository.findById(id);
		
		Employee entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return new EmployeeDTO(entity);
	}
	
	@Transactional
	public EmployeeDTO insert(EmployeeDTO dto) {
			
		Employee entity = new Employee();
		
		copyDtoToEntity(dto, entity);
		
		entity = repository.save(entity);
		
		return new EmployeeDTO(entity);
	}
	
	@Transactional
	public EmployeeDTO update(Long id, EmployeeDTO dto) {
		
		try {
			Employee entity = repository.getById(id);
			
			copyDtoToEntity(dto, entity);
			
			entity = repository.save(entity);
			
			return new EmployeeDTO(entity);
			
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
