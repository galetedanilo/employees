package com.galetedanilo.employee.responses;


import com.galetedanilo.employee.entities.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class DepartmentFullDetailsResponse extends DepartmentResponse {

    private Instant createdAt;
    private Instant updatedAt;

    public DepartmentFullDetailsResponse(Department department) {
        super(department);
        this.createdAt = department.getCreatedAt();
        this.updatedAt = department.getUpdatedAt();
    }
}
