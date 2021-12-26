package com.galetedanilo.employee.controllers;

import com.galetedanilo.employee.requests.DepartmentRequest;
import com.galetedanilo.employee.responses.DepartmentFullDetailsResponse;
import com.galetedanilo.employee.responses.DepartmentResponse;
import com.galetedanilo.employee.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

@RestController
@RequestMapping("/api/v1/departments")
@AllArgsConstructor
public class DepartmentController implements Serializable {

    private static final long serialVersionUID = 1L;

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<Page<DepartmentResponse>> findAllDepartments(Pageable pageable) {
        Page<DepartmentResponse> departmentResponsePage = departmentService.findAllDepartments(pageable);

        return new ResponseEntity<>(departmentResponsePage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DepartmentResponse> findDepartmentByPrimaryKey(@PathVariable Long id) {
        DepartmentResponse departmentResponse = departmentService.findDepartmentByPrimaryKey(id);

        return new ResponseEntity<>(departmentResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/details/{id}")
    public ResponseEntity<DepartmentFullDetailsResponse> findDepartmentByPrimaryKeyFullDetails(@PathVariable Long id) {
        DepartmentFullDetailsResponse departmentFullDetailsResponse = departmentService.findDepartmentByPrimaryKeyFullDetails(id);

        return new ResponseEntity<>(departmentFullDetailsResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse> saveNewDepartment(@RequestBody @Valid DepartmentRequest departmentRequest) {
        DepartmentResponse departmentResponse = departmentService.saveNewDepartment(departmentRequest);

        return new ResponseEntity<>(departmentResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(@PathVariable Long id, @RequestBody @Valid DepartmentRequest departmentRequest) {
        DepartmentResponse departmentResponse = departmentService.updateDepartment(id, departmentRequest);

        return new ResponseEntity<>(departmentResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteDepartmentByPrimaryKey(@PathVariable Long id) {
        departmentService.deleteDepartmentByPrimaryKey(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
