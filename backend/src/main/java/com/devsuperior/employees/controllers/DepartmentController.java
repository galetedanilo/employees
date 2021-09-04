package com.devsuperior.employees.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

import com.devsuperior.employees.requests.DepartmentRequest;
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
		
		response.forEach(x -> {
			x.add(WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(DepartmentController.class).findDepartmentById(x.getDepartmentId()))
					.withSelfRel());
			
			x.add(WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(DepartmentController.class).deleteDepartment(x.getDepartmentId()))
					.withRel("Delete Department"));
		});
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DepartmentResponse>  findDepartmentById(@PathVariable Long id) {
		
		DepartmentResponse response = service.findDepartmentById(id);
		
		response.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(DepartmentController.class).findAllDepartments(PageRequest.of(0, 20)))
				.withRel("Find All Departments"));
		
		response.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(DepartmentController.class).deleteDepartment(response.getDepartmentId()))
				.withRel("Delete Department"));
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<DepartmentResponse> addNewDepartment(@Validated @RequestBody DepartmentRequest request) {
		
		DepartmentResponse response = service.addNewDepartment(request);
		
		response.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(DepartmentController.class).findAllDepartments(PageRequest.of(0, 20)))
				.withRel("Find All Departments"));
		
		response.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(DepartmentController.class).deleteDepartment(response.getDepartmentId()))
				.withRel("Delete Department"));
		
		//URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		//			.buildAndExpand(response.getId()).toUri();
		
		//return ResponseEntity.created(uri).body(dto);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<DepartmentResponse> update(@PathVariable Long id, @Validated @RequestBody DepartmentRequest request) {
		
		DepartmentResponse response = service.updateDepartment(id, request);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
		
		service.deleteDepartment(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
