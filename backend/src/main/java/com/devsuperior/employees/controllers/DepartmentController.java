package com.devsuperior.employees.controllers;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.employees.dto.DepartmentDTO;
import com.devsuperior.employees.responses.DepartmentResponse;
import com.devsuperior.employees.services.DepartmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/app/v1/departments")
@AllArgsConstructor
public class DepartmentController {
	
	private final DepartmentService service;
	
	@GetMapping
	public ResponseEntity<Page<DepartmentResponse>> findAllDepartments(Pageable pageable) {
		
		Page<DepartmentResponse> response = service.findAllDepartments(pageable);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DepartmentDTO>  findById(@PathVariable Long id) {
		
		DepartmentDTO dto = service.findById(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<DepartmentDTO> insert(@Validated @RequestBody DepartmentDTO dto) {
		
		dto = service.insert(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<DepartmentDTO> update(@PathVariable Long id, @Validated @RequestBody DepartmentDTO dto) {
		
		dto = service.update(id, dto);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}

}
