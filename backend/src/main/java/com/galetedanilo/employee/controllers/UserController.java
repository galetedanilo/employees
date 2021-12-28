package com.galetedanilo.employee.controllers;

import com.galetedanilo.employee.responses.UserResponse;
import com.galetedanilo.employee.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> findAllUsers(Pageable pageable) {
        Page<UserResponse> userResponsePage = userService.findAllUsers(pageable);

        return new ResponseEntity<>(userResponsePage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> findUserByPrimaryKey(@PathVariable Long id) {
        UserResponse userResponse = userService.findUserByPrimaryKey(id);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUserByPrimaryKey(@PathVariable Long id) {
        userService.deleteUserByPrimaryKey(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
