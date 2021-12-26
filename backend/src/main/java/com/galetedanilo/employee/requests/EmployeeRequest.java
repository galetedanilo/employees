package com.galetedanilo.employee.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Employee first name is required")
    private String firstName;
    @NotBlank(message = "Employee last name is required")
    private String lastName;
    @NotBlank(message = "Employee genre is required")
    private String genre;
    @CPF(message = "CPF is not valid")
    private String cpf;
    @Past(message = "Employee birth date is not valid")
    private LocalDate birthDate;
    @NotBlank(message = "Employee username is required")
    private String username;
    @NotBlank(message = "Employee image uri is required")
    private String imageUri;
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Employee password is required")
    @Size(min = 8, max = 25, message = "Employee password must be between 8 to 25 characters")
    private String password;
    @NotBlank(message = "Employee confirm password is required")
    //https://memorynotfound.com/field-matching-bean-validation-annotation-example/
    private String confirmPassword;
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount of being a value greater than zero")
    @DecimalMax(value = "100000.00", inclusive = true, message = "Amount cannot be greater than one hundred thousand")
    @Digits(integer = 6, fraction = 2)
    private BigDecimal amount;
    @NotNull(message = "Employee department id is required")
    private Long departmentId;
}
