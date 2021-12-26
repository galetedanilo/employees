package com.galetedanilo.employee.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Department name is required")
    private String name;
    @NotBlank(message = "Department description is required")
    private String description;
}
