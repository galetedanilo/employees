package com.devsuperior.employees.responses;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse extends RepresentationModel<DepartmentResponse> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long departmentId;
	
	private String name;
}
