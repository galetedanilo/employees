package com.devsuperior.employees.requests;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest implements Serializable{

	private static final long serialVersionUID = 1l;
	
	@NotNull(message = "The id is required")
	private Long id;
	
	@NotBlank(message = "The name is required")
	private String name;
}
