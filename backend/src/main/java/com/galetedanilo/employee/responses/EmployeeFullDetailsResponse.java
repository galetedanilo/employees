package com.galetedanilo.employee.responses;

import com.galetedanilo.employee.entities.Employee;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class EmployeeFullDetailsResponse extends EmployeeResponse{

    private String cpf;
    private LocalDate birthDate;
    private Instant createdAt;
    private Instant updatedAt;

    public EmployeeFullDetailsResponse(Employee employee) {
        super(employee);
        this.cpf = employee.getCpf();
        this.birthDate = employee.getBirthDate();
        this.createdAt = employee.getCreatedAt();
        this.updatedAt = employee.getUpdatedAt();
    }
}
