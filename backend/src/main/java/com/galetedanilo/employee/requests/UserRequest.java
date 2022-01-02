package com.galetedanilo.employee.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "User password is required")
    @Size(min = 8, max = 25, message = "Employee password must be between 8 to 25 characters")
    private String password;
    private String confirmPassword;
    @Valid
    private Set<RoleRequest> roleRequestSet;
}
