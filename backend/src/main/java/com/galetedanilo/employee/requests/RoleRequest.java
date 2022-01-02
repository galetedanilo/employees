package com.galetedanilo.employee.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Role id is required")
    private Long id;
    @NotBlank(message = "Role authority is required")
    private String authority;

}
