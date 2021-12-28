package com.galetedanilo.employee.responses;

import com.galetedanilo.employee.controllers.UserController;
import com.galetedanilo.employee.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse extends RepresentationModel<UserResponse> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();

        this.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UserController.class).findUserByPrimaryKey(id))
                .withSelfRel()
        );

        this.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UserController.class).deleteUserByPrimaryKey(id))
                .withRel("Delete User")
        );

        this.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UserController.class).findAllUsers(PageRequest.of(0, 20)))
                .withRel("Find all users")
        );

    }
}
