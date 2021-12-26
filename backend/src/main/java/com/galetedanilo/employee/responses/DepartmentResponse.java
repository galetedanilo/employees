package com.galetedanilo.employee.responses;

import com.galetedanilo.employee.controllers.DepartmentController;
import com.galetedanilo.employee.entities.Department;
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
public class DepartmentResponse extends RepresentationModel<DepartmentResponse> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;

    public DepartmentResponse(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.description = department.getDescription();

        this.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(DepartmentController.class).findDepartmentByPrimaryKey(id))
                .withSelfRel()
        );

        this.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(DepartmentController.class).findDepartmentByPrimaryKeyFullDetails(id))
                .withRel("View full details")
        );

        this.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(DepartmentController.class).deleteDepartmentByPrimaryKey(id))
                .withRel("Delete department")
        );

        this.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(DepartmentController.class).findAllDepartments(PageRequest.of(0, 20)))
                .withRel("Find all departments")
        );
    }
}
