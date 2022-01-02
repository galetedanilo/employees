package com.galetedanilo.employee.responses;

import com.galetedanilo.employee.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String authority;

    public RoleResponse(Role role) {
        this.id = role.getId();
        this.authority = role.getAuthority();
    }
}
