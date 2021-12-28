package com.galetedanilo.employee.responses;

import com.galetedanilo.employee.controllers.EmployeeController;
import com.galetedanilo.employee.entities.Employee;
import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.io.Serializable;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse extends RepresentationModel<EmployeeResponse> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String imageUri;
    private String email;
    private DepartmentResponse departmentResponse;

    public EmployeeResponse(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.gender = employee.getGender().name();
        this.imageUri = employee.getImageUri();
        this.email = employee.getEmail();
        this.departmentResponse = new DepartmentResponse(employee.getDepartment());

        this.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).findEmployeeByPrimaryKey(id))
                .withSelfRel()
        );

        this.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).findEmployeeByPrimaryKeyFullDetails(id))
                .withRel("View full details")
        );

        this.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).deleteEmployeeByPrimaryKey(id))
                .withRel("Delete employee")
        );

        this.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).findAllEmployees(PageRequest.of(0, 20)))
                .withRel("Find all employees")
        );
    }
}
